package com.alexandrebcruz.moneyflow.application.usecase.transaction;

import com.alexandrebcruz.moneyflow.domain.CategoryRepository;
import com.alexandrebcruz.moneyflow.domain.TransactionRepository;
import com.alexandrebcruz.moneyflow.domain.exception.TransactionNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class DeleteTransactionUseCase {

    private final TransactionRepository txRepo;

    public DeleteTransactionUseCase(TransactionRepository txRepo, CategoryRepository categoryRepo) {
        this.txRepo = txRepo;
    }

    public void execute(UUID transactionId, UUID userId) {
        Objects.requireNonNull(transactionId, "transactionId must not be null");
        txRepo.findByIdAndUserId(transactionId, userId)
                .orElseThrow(TransactionNotFoundException::new);
        txRepo.deleteById(transactionId);
    }
}