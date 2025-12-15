package com.alexandrebcruz.moneyflow.adapters.in.web.dto;

import com.alexandrebcruz.moneyflow.domain.model.Transaction;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.util.UUID;

public record CreateTransactionRequest(
        @NotNull UUID categoryId,
        @Positive int amountCents,
        @NotNull Transaction.TransactionType type,
        @Size(max = 255) String description,
        @NotNull Instant occurredAt
) { }