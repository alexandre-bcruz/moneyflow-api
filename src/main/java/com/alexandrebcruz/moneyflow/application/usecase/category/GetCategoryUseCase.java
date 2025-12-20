package com.alexandrebcruz.moneyflow.application.usecase.category;

import com.alexandrebcruz.moneyflow.domain.CategoryRepository;
import com.alexandrebcruz.moneyflow.domain.model.Category;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class GetCategoryUseCase {

    private final CategoryRepository categoryRepository;

    GetCategoryUseCase(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public Category execute(UUID id){
        return categoryRepository.findById(id);
    }

    public Map<UUID, String> getNamesByIds(Set<UUID> ids) {
        var categories = categoryRepository.findAllById(ids);
        var names = new HashMap<UUID, String>();
        for(var cat : categories){
            names.put(cat.id(), cat.name());
        }
        return names;
    }
}
