package com.alexandrebcruz.moneyflow.application.usecase.transaction;

import com.alexandrebcruz.moneyflow.domain.CategoryRepository;
import com.alexandrebcruz.moneyflow.domain.TransactionRepository;
import com.alexandrebcruz.moneyflow.domain.model.Transaction;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

import static com.alexandrebcruz.moneyflow.adapters.in.web.util.MoneyUtils.fromMinorUnit;

@Service
public class CreateTransactionUseCase {

    private final TransactionRepository txRepo;

    public CreateTransactionUseCase(TransactionRepository txRepo, CategoryRepository categoryRepo) {
        this.txRepo = txRepo;
    }

    public Transaction execute(UUID userId, UUID categoryId, long amountCents, Transaction.TransactionType type, String description, Instant occurredAt) {
        if (amountCents <= 0) throw new IllegalArgumentException("amountCents must be positive");

        var tx = new Transaction(null, userId, categoryId, fromMinorUnit(amountCents, 2), type, description, occurredAt);
        return txRepo.save(tx);
    }
}