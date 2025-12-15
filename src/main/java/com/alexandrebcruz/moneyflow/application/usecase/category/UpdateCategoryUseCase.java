package com.alexandrebcruz.moneyflow.application.usecase.category;

import com.alexandrebcruz.moneyflow.domain.CategoryRepository;
import com.alexandrebcruz.moneyflow.domain.model.Category;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateCategoryUseCase {

    private final CategoryRepository repository;

    UpdateCategoryUseCase(CategoryRepository repository) {
        this.repository = repository;
    }

    public Category execute(UUID categoryId, UUID userId, String name) {

        var existing = repository.findByIdAndUserId(categoryId, userId).orElseThrow(() -> new IllegalStateException("Category not found"));
        if(name == null || name.isBlank()) throw new IllegalStateException("Invalid name");
        if(existing.name().equals(name)) throw new IllegalStateException("No change identify");

        var updated = new Category(
                categoryId,
                userId,
                name
        );
        return repository.save(updated);
    }
}
