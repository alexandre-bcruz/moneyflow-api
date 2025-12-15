package com.alexandrebcruz.moneyflow.application.usecase.category;

import com.alexandrebcruz.moneyflow.adapters.out.persistence.category.CategoryRepositoryAdapter;
import com.alexandrebcruz.moneyflow.domain.exception.CategoryNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class DeleteCategoryUseCase {

    private final CategoryRepositoryAdapter categoryRepository;

    DeleteCategoryUseCase(CategoryRepositoryAdapter categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public void execute(UUID categoryId, UUID userId){
        Objects.requireNonNull(userId, "userId is required");
        categoryRepository.findByIdAndUserId(categoryId, userId)
                .orElseThrow(CategoryNotFoundException::new);
        categoryRepository.deleteById(categoryId);
    }
}
