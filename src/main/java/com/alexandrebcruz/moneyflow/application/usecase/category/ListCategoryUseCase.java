package com.alexandrebcruz.moneyflow.application.usecase.category;

import com.alexandrebcruz.moneyflow.domain.CategoryRepository;
import com.alexandrebcruz.moneyflow.domain.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ListCategoryUseCase {

    private final CategoryRepository categoryRepository;

    ListCategoryUseCase(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public List<Category> execute(UUID userId){
        Objects.requireNonNull(userId, "userId is required");
        return   categoryRepository.findAllByUserId(userId);
    }
}
