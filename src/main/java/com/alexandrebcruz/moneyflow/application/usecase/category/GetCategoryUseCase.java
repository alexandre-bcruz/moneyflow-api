package com.alexandrebcruz.moneyflow.application.usecase.category;

import com.alexandrebcruz.moneyflow.domain.CategoryRepository;
import com.alexandrebcruz.moneyflow.domain.model.Category;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class GetCategoryUseCase {

    private final CategoryRepository categoryRepository;

    GetCategoryUseCase(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public Category execute(UUID id){
        return categoryRepository.findById(id);
    }
}
