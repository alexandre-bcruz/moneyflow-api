package com.alexandre_bcruz.moneyflow_api.controller;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HealthControllerTest {


    private final HealthController healthController;

    HealthControllerTest(){
        this.healthController = new HealthController();
    }

    @Test
    void testHealth(){
        assertEquals(Map.of("status","ok"), healthController.health());
    }

}