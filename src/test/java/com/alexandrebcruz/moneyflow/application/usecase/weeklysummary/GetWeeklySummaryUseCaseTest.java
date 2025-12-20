package com.alexandrebcruz.moneyflow.application.usecase.weeklysummary;

import com.alexandrebcruz.moneyflow.application.usecase.category.GetCategoryUseCase;
import com.alexandrebcruz.moneyflow.application.usecase.transaction.ListTransactionUseCase;
import com.alexandrebcruz.moneyflow.domain.model.Transaction;
import com.alexandrebcruz.moneyflow.util.CategoryFixtures;
import com.alexandrebcruz.moneyflow.util.TransactionFixtures;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.alexandrebcruz.moneyflow.domain.model.Transaction.TransactionType.EXPENSE;
import static com.alexandrebcruz.moneyflow.domain.model.Transaction.TransactionType.INCOME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetWeeklySummaryUseCaseTest {

    GetWeeklySummaryUseCase getWeeklySummaryUseCase;

    ListTransactionUseCase listTransactionUseCase = mock(ListTransactionUseCase.class);
    GetCategoryUseCase getCategoryUseCase = mock(GetCategoryUseCase.class);

    public static final UUID USER_ID = UUID.fromString("00000000-0000-0000-0000-000000000001");

    GetWeeklySummaryUseCaseTest() {
        this.getWeeklySummaryUseCase = new GetWeeklySummaryUseCase(listTransactionUseCase, getCategoryUseCase);
    }

    @Test
    void execute() {

        var from = Instant.now().minus(2, ChronoUnit.DAYS);
        var to = from.plus(7, ChronoUnit.DAYS);

        var mockTransactions = TransactionFixtures.mixedTransactions(from, to);
        when(listTransactionUseCase.execute(USER_ID, from, to))
                .thenReturn(mockTransactions);

        var catIds = mockTransactions.stream().map(Transaction::categoryId).collect(Collectors.toSet());


        when(getCategoryUseCase.getNamesByIds(catIds)).thenReturn(CategoryFixtures.categoryNames());

        var response = getWeeklySummaryUseCase.execute(from, USER_ID);

        var expectedInRange = mockTransactions.stream()
                .filter(t ->
                        !t.occurredAt().isBefore(from) &&
                                t.occurredAt().isBefore(to))
                .toList();

        var expectedExpense = expectedInRange.stream()
                .filter(t -> t.type() == EXPENSE)
                .map(Transaction::amountCurrency)
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        var expectedIncome = expectedInRange.stream()
                .filter(t -> t.type() == INCOME)
                .map(Transaction::amountCurrency)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        var expectedBalance = expectedIncome.subtract(expectedExpense);

        assertEquals(expectedExpense, response.expense());
        assertEquals(expectedIncome, response.income());
        assertEquals((expectedBalance), response.balance());


    }
}