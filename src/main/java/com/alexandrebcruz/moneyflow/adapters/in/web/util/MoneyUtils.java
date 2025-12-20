package com.alexandrebcruz.moneyflow.adapters.in.web.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class MoneyUtils {

    private MoneyUtils() {}

    public static BigDecimal roundMoney(BigDecimal value) {
        return value.setScale(2, RoundingMode.HALF_UP);
    }

    public static double roundPercent(double value) {
        return BigDecimal.valueOf(value)
                .setScale(1, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public static long toMinorUnit(double value, int fractionDigits) {
        return BigDecimal.valueOf(value)
                .movePointRight(fractionDigits)
                .setScale(0, RoundingMode.HALF_UP)
                .intValue();
    }

    public static BigDecimal fromMinorUnit(long value, int fractionDigits) {
        return BigDecimal.valueOf(value)
                .movePointLeft(fractionDigits)
                .setScale(fractionDigits, RoundingMode.HALF_UP);
    }
}