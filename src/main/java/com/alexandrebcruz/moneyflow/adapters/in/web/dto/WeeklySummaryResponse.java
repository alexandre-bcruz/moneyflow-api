package com.alexandrebcruz.moneyflow.adapters.in.web.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record WeeklySummaryResponse(
        Instant weekStart,
        Instant weekEnd,
        String currency,
        BigDecimal income,
        BigDecimal expense,
        BigDecimal balance,
        int totalTransactions,
        List<CategoryExpenseBreakdown> topExpenses
) {}

