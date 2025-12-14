package com.alexandrebcruz.moneyflow.adapters.out.persistence;

import com.alexandrebcruz.moneyflow.domain.CategoryRepository;
import com.alexandrebcruz.moneyflow.domain.model.Category;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CategoryRepositoryAdapter implements CategoryRepository {

    private final SpringDataCategoryJpaRepository jpa;

    CategoryRepositoryAdapter(SpringDataCategoryJpaRepository jpa){
        this.jpa = jpa;
    }


    @Override
    public Category save(Category category) {
        var saved = jpa.save(new CategoryEntity(category.userId(), category.name()));
        return new Category(saved.getId(), saved.getUserId(), saved.getName());
    }

    @Override
    public void deleteById(UUID id) {
        jpa.deleteById(id);
    }

    @Override
    public List<Category> findAllByUserId(UUID userId) {
        return jpa.findAllByUserId(userId)
                .stream()
                .map(e -> new Category(e.id(), e.userId(), e.name()))
                .toList();
    }

    @Override
    public Optional<Category> findByIdAndUserId(UUID id, UUID userId) {
        return jpa.findByIdAndUserId(id, userId);
    }

    @Override
    public boolean existsByUserIdAndNameIgnoreCase(UUID userId, String name) {
        return jpa.existsByUserIdAndNameIgnoreCase(userId, name);
    }
}
