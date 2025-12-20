package com.alexandrebcruz.moneyflow.domain;

import com.alexandrebcruz.moneyflow.domain.model.Category;

import java.util.*;

public interface CategoryRepository {
    Category save(Category category);
    void deleteById(UUID id);

    boolean existsByUserIdAndNameIgnoreCase(UUID userId, String name);
    List<Category> findAllByUserId(UUID userId);
    Optional<Category> findByIdAndUserId(UUID id, UUID userId);
    Category findById(UUID id);
    List<Category> findAllById(Set<UUID> ids);
}
