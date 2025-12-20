package com.alexandrebcruz.moneyflow.adapters.out.persistence.transactions;

import com.alexandrebcruz.moneyflow.domain.TransactionRepository;
import com.alexandrebcruz.moneyflow.domain.model.Transaction;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.alexandrebcruz.moneyflow.adapters.in.web.util.MoneyUtils.fromMinorUnit;
import static com.alexandrebcruz.moneyflow.adapters.in.web.util.MoneyUtils.toMinorUnit;
import static java.util.Optional.ofNullable;

@Repository
public class TransactionRepositoryAdapter implements TransactionRepository {

    private final SpringDataTransactionJpaRepository jpa;

    TransactionRepositoryAdapter(SpringDataTransactionJpaRepository jpa){
        this.jpa = jpa;
    }


    @Override
    public Transaction save(Transaction tx) {

        var occuredAt = ofNullable(tx.occurredAt()).stream().findFirst()
                .orElse(Instant.now());

        var entity = new TransactionEntity(
                tx.userId(),
                tx.categoryId(),
                (int) toMinorUnit(tx.amountCurrency().doubleValue(),2),
                tx.type(),
                tx.description(),
                occuredAt
        );

        var saved = jpa.save(entity);
        return toDomain(saved);
    }

    @Override
    public void deleteById(UUID id) {
        jpa.deleteById(id);
    }

    @Override
    public List<Transaction> findByUserIdAndRange(UUID userId, Instant from, Instant to) {
        return jpa.findAllByUserIdAndOccurredAtBetweenOrderByOccurredAtDesc(userId, from, to)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public List<Transaction> findByUserIdAndRangeAndCategory(UUID userId, Instant from, Instant to, UUID categoryId) {
        return jpa.findAllByUserIdAndCategoryIdAndOccurredAtBetweenOrderByOccurredAtDesc(userId, categoryId, from, to)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public Optional<Transaction> findByIdAndUserId(UUID txId, UUID userId) {
        return jpa.findByIdAndUserId(txId, userId);
    }

    private Transaction toDomain(TransactionEntity e) {
        return new Transaction(
                e.getId(),
                e.getUserId(),
                e.getCategoryId(),
                fromMinorUnit(e.getAmountCents(), 2),
                e.getType(),
                e.getDescription(),
                e.getOccurredAt()
        );
    }

}

