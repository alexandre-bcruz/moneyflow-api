package com.alexandrebcruz.moneyflow.application.usecase.category;

import com.alexandrebcruz.moneyflow.domain.CategoryRepository;
import com.alexandrebcruz.moneyflow.domain.model.Category;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateCategoryUseCase {

    private final CategoryRepository repository;

    CreateCategoryUseCase(CategoryRepository repository){
        this.repository = repository;
    }

    public Category execute(UUID userId, String name){
        var normalized = name.trim();
        if(normalized.isBlank()) throw new IllegalArgumentException("nameins blank");

        if(repository.existsByUserIdAndNameIgnoreCase(userId, normalized)){
            throw new IllegalStateException("category already exists");
        }
        return repository.save(new Category(null, userId, normalized));
    }
}
