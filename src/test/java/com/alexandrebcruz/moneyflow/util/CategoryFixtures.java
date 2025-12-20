package com.alexandrebcruz.moneyflow.util;


import java.util.Map;
import java.util.UUID;

public final class CategoryFixtures {

    private CategoryFixtures() {}

    public static final UUID CATEGORY_TRAVEL =
            UUID.fromString("9096619b-81b2-492c-89d9-5abe47f2164f");

    public static final UUID CATEGORY_BUSINESS =
            UUID.fromString("9126559b-81b2-502c-89d9-5abe39f2164f");

    public static final UUID CATEGORY_FOOD =
            UUID.fromString("7101c9a7-c7d3-4679-a531-e18f3d3e462d");

    /** Simula retorno em lote do GetCategoryUseCase */
    public static Map<UUID, String> categoryNames() {
        return Map.of(
                CATEGORY_TRAVEL, "Travel",
                CATEGORY_FOOD, "Food",
                CATEGORY_BUSINESS, "Business"
        );
    }
}