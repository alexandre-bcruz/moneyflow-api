package com.alexandrebcruz.moneyflow.adapters.out.persistence;

import com.alexandrebcruz.moneyflow.domain.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpringDataCategoryJpaRepository extends JpaRepository<CategoryEntity, UUID> {
    List<Category> findAllByUserId(UUID userId);
    Optional<Category> findByIdAndUserId(UUID id, UUID userId);
    boolean existsByUserIdAndNameIgnoreCase(UUID userId, String name);
}
