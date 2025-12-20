package com.alexandrebcruz.moneyflow.util;

import com.alexandrebcruz.moneyflow.domain.model.Transaction;
import com.alexandrebcruz.moneyflow.domain.model.Transaction.TransactionType;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

import static com.alexandrebcruz.moneyflow.adapters.in.web.controller.TransactionController.USER_ID;
import static com.alexandrebcruz.moneyflow.domain.model.Transaction.TransactionType.EXPENSE;
import static com.alexandrebcruz.moneyflow.domain.model.Transaction.TransactionType.INCOME;
import static com.alexandrebcruz.moneyflow.util.CategoryFixtures.*;

public final class TransactionFixtures {

    private TransactionFixtures() {}

    private static final Instant DEC_13 =
            Instant.parse("2025-12-13T18:30:00Z");

    private static final Instant DEC_11 =
            Instant.parse("2025-12-11T18:30:00Z");

    public static List<Transaction> mixedTransactions(Instant from, Instant to) {

        var firstDate = from.plus(2, ChronoUnit.DAYS);
        var secondDate = to.minus(2, ChronoUnit.DAYS);
        return List.of(
                expense("67544802-6620-4ae3-b5be-a370f33ff180",
                        CATEGORY_TRAVEL, 275.20,
                        "Passagem aérea – São Paulo → Noronha", secondDate),

                expense("bbd62a6f-6d84-4460-b5eb-d7ee83e454c8",
                        CATEGORY_TRAVEL, 120.99,
                        "Hospedagem – Pousada em Noronha", secondDate),

                income("7f2c093d-40ec-405a-b1c8-f17e26d36c35",
                        CATEGORY_BUSINESS, 150.00,
                        "Pagamento PJ – Projeto Backend", secondDate),

                income("013eb8e0-49ed-4e2c-b94a-22ba93463d50",
                        CATEGORY_BUSINESS, 251.99,
                        "Recebimento freelance – API Financeira", secondDate),

                expense("cbe75a93-fe0d-4d4f-873d-788d51a83db8",
                        CATEGORY_FOOD, 150.00,
                        "Almoço – Restaurante local", firstDate),

                expense("21b4e0f9-9134-4cc0-8151-260fa6aeac9d",
                        CATEGORY_FOOD, 50.00,
                        "Café da manhã – Padaria", firstDate)
        );
    }


    private static Transaction expense(
            String id, UUID categoryId, double amount,
            String description, Instant occurredAt
    ) {
        return build(id, categoryId, amount, EXPENSE, description, occurredAt);
    }

    private static Transaction income(
            String id, UUID categoryId, double amount,
            String description, Instant occurredAt
    ) {
        return build(id, categoryId, amount, INCOME, description, occurredAt);
    }

    private static Transaction build(
            String id,
            UUID categoryId,
            double amount,
            TransactionType type,
            String description,
            Instant occurredAt
    ) {
        return new Transaction(
                UUID.fromString(id),
                USER_ID,
                categoryId,
                BigDecimal.valueOf(amount),
                type,
                description,
                occurredAt
        );
    }
}