package com.alexandrebcruz.moneyflow.adapters.in.web.dto;

import com.alexandrebcruz.moneyflow.domain.model.Transaction;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record TransactionResponse(
        UUID id,
        UUID categoryId,
        BigDecimal amountCurrency,
        Transaction.TransactionType type,
        String description,
        Instant occurredAt
) {}