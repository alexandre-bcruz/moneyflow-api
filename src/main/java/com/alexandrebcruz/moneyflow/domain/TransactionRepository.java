package com.alexandrebcruz.moneyflow.domain;

import com.alexandrebcruz.moneyflow.domain.model.Transaction;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository {
    Transaction save(Transaction tx);
    void deleteById(UUID id);
    List<Transaction> findByUserIdAndRange(UUID userId, Instant from, Instant to);
    List<Transaction> findByUserIdAndRangeAndCategory(UUID userId, Instant from, Instant to, UUID categoryId);
    Optional<Transaction> findByIdAndUserId(UUID txId, UUID userId);
}