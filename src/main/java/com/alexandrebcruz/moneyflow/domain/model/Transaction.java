package com.alexandrebcruz.moneyflow.domain.model;

import java.time.Instant;
import java.util.UUID;

public record Transaction(
        UUID id,
        UUID userId,
        UUID categoryId,
        int amountCents,
        TransactionType type,
        String description,
        Instant occurredAt
) {

    public enum TransactionType { INCOME, EXPENSE }
}