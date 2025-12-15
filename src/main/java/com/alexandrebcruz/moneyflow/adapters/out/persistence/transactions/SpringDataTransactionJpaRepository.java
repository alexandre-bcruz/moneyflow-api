package com.alexandrebcruz.moneyflow.adapters.out.persistence.transactions;

import com.alexandrebcruz.moneyflow.domain.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpringDataTransactionJpaRepository extends JpaRepository<TransactionEntity, UUID> {
    List<TransactionEntity> findAllByUserIdAndOccurredAtBetweenOrderByOccurredAtDesc(
            UUID userId, Instant from, Instant to
    );

    List<TransactionEntity> findAllByUserIdAndCategoryIdAndOccurredAtBetweenOrderByOccurredAtDesc(
            UUID userId, UUID categoryId, Instant from, Instant to
    );
    Optional<Transaction> findByIdAndUserId(UUID txId, UUID userId);

}
