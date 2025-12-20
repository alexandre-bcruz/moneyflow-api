package com.alexandrebcruz.moneyflow.application.usecase.weeklysummary;

import com.alexandrebcruz.moneyflow.adapters.in.web.dto.CategoryExpenseBreakdown;
import com.alexandrebcruz.moneyflow.adapters.in.web.dto.WeeklySummaryResponse;
import com.alexandrebcruz.moneyflow.application.usecase.category.GetCategoryUseCase;
import com.alexandrebcruz.moneyflow.application.usecase.transaction.ListTransactionUseCase;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.alexandrebcruz.moneyflow.adapters.in.web.util.MoneyUtils.*;
import static com.alexandrebcruz.moneyflow.domain.model.Transaction.TransactionType.EXPENSE;
import static com.alexandrebcruz.moneyflow.domain.model.Transaction.TransactionType.INCOME;

@Service
public class GetWeeklySummaryUseCase {

    private final ListTransactionUseCase listTransactionUseCase;
    private final GetCategoryUseCase getCategoryUseCase;

    public GetWeeklySummaryUseCase(ListTransactionUseCase listTransactionUseCase, GetCategoryUseCase getCategoryUseCase) {
        this.listTransactionUseCase = listTransactionUseCase;
        this.getCategoryUseCase = getCategoryUseCase;
    }

    public WeeklySummaryResponse execute(Instant from, UUID userId) {

        var to = from.atOffset(ZoneOffset.UTC).plusDays(7).toInstant();
        var transactions = listTransactionUseCase.execute(userId, from, to);


        long totalIncome = 0;
        long totalExpense = 0;
        var expenseByCategory= new HashMap<UUID, Long>();

        for ( var tx : transactions ) {
            var amountCents = toMinorUnit(tx.amountCurrency(), 2);

            if(tx.type() == INCOME) {
                totalIncome += amountCents;
                continue;
            }
            if(tx.type() == EXPENSE) {
                totalExpense += amountCents;

                var carId = tx.categoryId();
                if(carId != null ){
                    expenseByCategory.merge(carId, amountCents, Long::sum);
                }
            }
        }


        var breakdownBase = expenseByCategory.entrySet().stream()
                .sorted((a, b) -> Long.compare(b.getValue(), a.getValue()))
                .limit(5)
                .toList();

        var categoryIds = breakdownBase.stream().map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        var categories = getCategoryUseCase.getNamesByIds(categoryIds);


        final long finalTotalExpense = totalExpense;
        var breakdown = breakdownBase.stream().map(
                e -> new CategoryExpenseBreakdown(
                        e.getKey(),
                        categories.getOrDefault(e.getKey(), "unknown"),
                        fromMinorUnit(e.getValue(), 2),
                        roundPercent(finalTotalExpense == 0 ? 0.0 : (e.getValue() * 100.0 / finalTotalExpense))
                )
        ).toList();

        var income = fromMinorUnit(totalIncome, 2);
        var expense = fromMinorUnit(totalExpense, 2);
        var balance = income.subtract(expense);

        return new WeeklySummaryResponse(from, to, "BRL",
                income,
                expense,
                balance,
                transactions.size(), breakdown);
    }
}
