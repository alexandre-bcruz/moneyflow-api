package com.alexandrebcruz.moneyflow.adapters.in.web.controller;

import com.alexandrebcruz.moneyflow.adapters.in.web.dto.CategoryResponse;
import com.alexandrebcruz.moneyflow.adapters.in.web.dto.CreateCategoryRequest;
import com.alexandrebcruz.moneyflow.application.usecase.category.*;
import com.alexandrebcruz.moneyflow.domain.model.Category;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/categories")
public class CategoryController {


    //  provisorio ate o JWT
    private static final UUID USER_ID = UUID.fromString("00000000-0000-0000-0000-000000000001");

    private final CreateCategoryUseCase createCategoryUseCase;
    private final ListCategoryUseCase listCategoryUseCase;
    private final DeleteCategoryUseCase deleteCategoryUseCase;
    private final UpdateCategoryUseCase updateCategoryUseCase;
    private final GetCategoryUseCase getCategoryUseCase;

    protected CategoryController(CreateCategoryUseCase createUseCase, ListCategoryUseCase listCategoryUseCase,
             DeleteCategoryUseCase deleteCategoryUseCase, UpdateCategoryUseCase updateCategoryUseCase, GetCategoryUseCase getCategoryUseCase){
        this.createCategoryUseCase = createUseCase;
        this.listCategoryUseCase = listCategoryUseCase;
        this.deleteCategoryUseCase = deleteCategoryUseCase;
        this.updateCategoryUseCase = updateCategoryUseCase;
        this.getCategoryUseCase = getCategoryUseCase;
    }


    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CreateCategoryRequest request){
        var created = createCategoryUseCase.execute(USER_ID, request.name());
        var body = new CategoryResponse(created.id(), created.name());
        return ResponseEntity.created(URI.create("/categories/" + created.id())).body(body);
    }

    @GetMapping
    ResponseEntity<List<CategoryResponse>> list(){
        var list = listCategoryUseCase.execute(USER_ID)
                .stream()
                .map(c -> new CategoryResponse(c.id(), c.name()))
                .toList();
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable UUID id){
        deleteCategoryUseCase.execute(id, USER_ID);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    ResponseEntity<CategoryResponse> update(@PathVariable UUID id, @RequestBody Category category){
        var body = updateCategoryUseCase.execute(id, USER_ID, category.name());
        return ResponseEntity.ok().body(new CategoryResponse(body.id(), body.name()));
    }

    @GetMapping("/{id}")
    ResponseEntity<CategoryResponse> get(@PathVariable UUID id){
        var body = getCategoryUseCase.execute(id);
        return ResponseEntity.ok().body(new CategoryResponse(body.id(), body.name()));
    }
}
