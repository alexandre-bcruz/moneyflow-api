package com.alexandrebcruz.moneyflow.adapters.in.web.controller;

import com.alexandrebcruz.moneyflow.adapters.in.web.dto.CreateTransactionRequest;
import com.alexandrebcruz.moneyflow.adapters.in.web.dto.TransactionResponse;
import com.alexandrebcruz.moneyflow.application.usecase.transaction.*;
import com.alexandrebcruz.moneyflow.domain.model.Transaction;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.Instant;
import java.util.UUID;

import static com.alexandrebcruz.moneyflow.adapters.in.web.util.MoneyUtils.toMinorUnit;
import static java.util.Objects.requireNonNull;

@RestController
@RequestMapping("/transactions")
public class TransactionController {


    //  provisorio ate o JWT
    public static final UUID USER_ID = UUID.fromString("00000000-0000-0000-0000-000000000001");

    private final CreateTransactionUseCase createTransactionUseCase;
    private final ListTransactionUseCase listTransactionUseCase;
    private final DeleteTransactionUseCase deleteTransactionUseCase;
    private final UpdateTransactionUseCase updateTransactionUseCase;
    private final GetTransactionUseCase getTransactionUseCase;

    TransactionController(CreateTransactionUseCase createTransactionUseCase, ListTransactionUseCase listTransactionUseCase,
                          DeleteTransactionUseCase deleteTransactionUseCase, UpdateTransactionUseCase updateTransactionUseCase,
                          GetTransactionUseCase getTransactionUseCase){
        this.createTransactionUseCase = createTransactionUseCase;
        this.listTransactionUseCase = listTransactionUseCase;
        this.deleteTransactionUseCase = deleteTransactionUseCase;
        this.updateTransactionUseCase = updateTransactionUseCase;
        this.getTransactionUseCase = getTransactionUseCase;
    }


    @PostMapping
    public ResponseEntity<TransactionResponse> create(@Valid @RequestBody CreateTransactionRequest req){
        requireNonNull(req.amountCurrency(), "valor da transacao deve ser informado.");
        var amountCents = toMinorUnit(req.amountCurrency(), 2);
        var created = createTransactionUseCase.execute(USER_ID, req.categoryId(), amountCents, req.type(), req.description(), req.occurredAt());
        var body = new TransactionResponse(created.id(), created.categoryId(), created.amountCurrency(), created.type(), created.description(), created.occurredAt());
        return ResponseEntity.created(URI.create("/transactions/" + created.id())).body(body);
    }

    @GetMapping
    public ResponseEntity<?> list(@RequestParam Instant from, @RequestParam Instant to, @RequestParam(required = false) UUID categoryId){
        var list = listTransactionUseCase.execute(USER_ID, from, to, categoryId)
                .stream()
                .map(c -> new TransactionResponse(c.id(), c.categoryId(), c.amountCurrency(), c.type(), c.description(), c.occurredAt()))
                .toList();
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> get(@PathVariable UUID id){
        return ResponseEntity.ok(getTransactionUseCase.execute(id, USER_ID));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Transaction> update(@PathVariable UUID id, Transaction trasaction){
        var transaction = updateTransactionUseCase.execute(id, USER_ID, trasaction);
        return ResponseEntity.ok(transaction);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        deleteTransactionUseCase.execute(id, USER_ID);
        return ResponseEntity.noContent().build();
    }
}
