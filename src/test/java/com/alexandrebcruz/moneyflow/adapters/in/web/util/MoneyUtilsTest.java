package com.alexandrebcruz.moneyflow.adapters.in.web.util;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyUtilsTest {

    @Test
    void roundMoneyDown() {
        assertEquals(BigDecimal.valueOf(10.93), MoneyUtils.roundMoney(BigDecimal.valueOf(10.9329)));
    }
    @Test
    void roundMoneyUp() {
        assertEquals(BigDecimal.valueOf(10.94), MoneyUtils.roundMoney(BigDecimal.valueOf(10.9379)));
    }

    @Test
    void roundPercent() {
        assertEquals(10.9, MoneyUtils.roundPercent(10.9389));
    }

    @Test
    void toMinorUnit() {

        assertEquals(
                new BigDecimal("10.29"),
                MoneyUtils.fromMinorUnit(1029, 2)
        );
    }


    @Test
    void fromMinorUnit() {
        assertEquals(
                1029,
                MoneyUtils.toMinorUnit(new BigDecimal("10.29"), 2)
        );
    }
}