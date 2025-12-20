package com.alexandrebcruz.moneyflow.adapters.out.persistence.category;

import com.alexandrebcruz.moneyflow.domain.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface SpringDataCategoryJpaRepository extends JpaRepository<CategoryEntity, UUID> {
    List<Category> findAllByUserId(UUID userId);
    Optional<Category> findByIdAndUserId(UUID id, UUID userId);
    boolean existsByUserIdAndNameIgnoreCase(UUID userId, String name);

    @Query("select c from CategoryEntity c where c.id in :ids")
    List<Category> findAllById(@Param("ids") Set<UUID> ids);
}
