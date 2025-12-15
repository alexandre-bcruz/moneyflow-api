package com.alexandrebcruz.moneyflow.application.usecase.transaction;

import com.alexandrebcruz.moneyflow.domain.TransactionRepository;
import com.alexandrebcruz.moneyflow.domain.exception.TransactionNotFoundException;
import com.alexandrebcruz.moneyflow.domain.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class GetTransactionUseCase {

    private final TransactionRepository txRepository;

    GetTransactionUseCase(TransactionRepository txRepository){
        this.txRepository = txRepository;
    }


    public Transaction execute(UUID id, UUID userId){
        return txRepository.findByIdAndUserId(id, userId)
                .orElseThrow((TransactionNotFoundException::new));
    }
}
