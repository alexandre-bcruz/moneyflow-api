package com.alexandrebcruz.moneyflow.application.usecase.transaction;

import com.alexandrebcruz.moneyflow.domain.TransactionRepository;
import com.alexandrebcruz.moneyflow.domain.model.Transaction;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;


@Service
public class ListTransactionUseCase {

    private final TransactionRepository txRepository;

    ListTransactionUseCase(TransactionRepository txRepository){
        this.txRepository = txRepository;
    }

    public List<Transaction> execute(UUID userId, Instant from, Instant to, UUID categoryId) {
        return (categoryId != null )
                ? txRepository.findByUserIdAndRangeAndCategory(userId, from, to, categoryId)
                : txRepository.findByUserIdAndRange(userId, from, to);
    }

    public List<Transaction> execute(UUID userId, Instant from, Instant to) {
        return txRepository.findByUserIdAndRange(userId, from, to);
    }

}
