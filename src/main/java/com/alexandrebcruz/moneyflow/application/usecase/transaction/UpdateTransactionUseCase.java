package com.alexandrebcruz.moneyflow.application.usecase.transaction;

import com.alexandrebcruz.moneyflow.domain.CategoryRepository;
import com.alexandrebcruz.moneyflow.domain.TransactionRepository;
import com.alexandrebcruz.moneyflow.domain.model.Transaction;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

import static com.alexandrebcruz.moneyflow.adapters.in.web.util.MoneyUtils.fromMinorUnit;
import static com.alexandrebcruz.moneyflow.adapters.in.web.util.MoneyUtils.toMinorUnit;

@Service
public class UpdateTransactionUseCase {

    private final TransactionRepository txRepo;
    private final CategoryRepository categoryRepo;

    public UpdateTransactionUseCase(TransactionRepository txRepo, CategoryRepository categoryRepo) {
        this.txRepo = txRepo;
        this.categoryRepo = categoryRepo;
    }

    public Transaction execute( UUID txId, UUID userId,
                               Transaction transaction) {

        Objects.requireNonNull(userId, "userId is required");
        Objects.requireNonNull(txId, "txId is required");

        var categoryId = transaction.categoryId();
        var amountCents = toMinorUnit(transaction.amountCurrency().doubleValue(), 2);
        var type = transaction.type();
        var description = transaction.description();
        var occurredAt = transaction.occurredAt();

        var existing = txRepo.findByIdAndUserId(txId, userId)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found"));

        boolean changed = false;

        UUID newCategoryId = existing.categoryId();
        if (categoryId != null && !categoryId.equals(existing.categoryId())) {
            categoryRepo.findByIdAndUserId(categoryId, userId)
                    .orElseThrow(() -> new IllegalArgumentException("Category not found"));
            newCategoryId = categoryId;
            changed = true;
        }

        long newAmount = toMinorUnit(existing.amountCurrency().doubleValue(), 2);
        if (amountCents != newAmount) {
            if (amountCents <= 0) throw new IllegalArgumentException("amountCents must be positive");
            newAmount = amountCents;
            changed = true;
        }

        var newType = existing.type();
        if (type != null && type != existing.type()) {
            newType = type;
            changed = true;
        }

        String newDesc = existing.description();
        if (description != null && !description.equals(existing.description())) {
            if (description.isBlank()) throw new IllegalArgumentException("description must not be blank");
            if (description.length() > 255) throw new IllegalArgumentException("description too long");
            newDesc = description;
            changed = true;
        }

        Instant newOccurredAt = existing.occurredAt();
        if (occurredAt != null && !occurredAt.equals(existing.occurredAt())) {
            newOccurredAt = occurredAt;
            changed = true;
        }

        if (!changed) {
            return existing; // nada mudou
        }

        var updated = new Transaction(
                existing.id(),
                existing.userId(),
                newCategoryId,
                fromMinorUnit(newAmount,2),
                newType,
                newDesc,
                newOccurredAt
        );

        return txRepo.save(updated);
    }
}