package com.alexandrebcruz.moneyflow.domain.model;

import java.util.UUID;

public record  Category(
        UUID id, UUID userId, String name
){}

