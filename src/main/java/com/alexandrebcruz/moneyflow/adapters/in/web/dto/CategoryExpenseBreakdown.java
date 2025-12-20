package com.alexandrebcruz.moneyflow.adapters.in.web.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record CategoryExpenseBreakdown(
        UUID categoryId,
        String categoryName,
        BigDecimal expense,
        double sharePct
) {}