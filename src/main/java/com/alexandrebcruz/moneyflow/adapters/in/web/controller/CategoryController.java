package com.alexandrebcruz.moneyflow.adapters.in.web.controller;

import com.alexandrebcruz.moneyflow.adapters.in.web.dto.CategoryResponse;
import com.alexandrebcruz.moneyflow.adapters.in.web.dto.CreateCategoryRequest;
import com.alexandrebcruz.moneyflow.application.usecase.CreateCategoryUseCase;
import com.alexandrebcruz.moneyflow.domain.CategoryRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
public class CategoryController {


    //  provisorio ate o JWT
    private static final UUID USER_ID = UUID.fromString("00000000-0000-0000-0000-000000000001");

    private final CreateCategoryUseCase createCategoryUseCase;
    private final CategoryRepository categoryRepository;

    CategoryController(CreateCategoryUseCase createUseCase, CategoryRepository categoryRepository){
        this.createCategoryUseCase = createUseCase;
        this.categoryRepository = categoryRepository;
    }


    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CreateCategoryRequest request){
        var created = createCategoryUseCase.execute(USER_ID, request.name());
        var body = new CategoryResponse(created.id(), created.name());
        return ResponseEntity.created(URI.create("/categories/" + created.id())).body(body);
    }

    @GetMapping
    ResponseEntity<?> list(){
        var list = categoryRepository.findAllByUserId(USER_ID)
                .stream()
                .map(c -> new CategoryResponse(c.id(), c.name()))
                .toList();
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable UUID id){
        categoryRepository.findAllByUserId(id);
        categoryRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
