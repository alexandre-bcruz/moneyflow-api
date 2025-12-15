package com.alexandrebcruz.moneyflow.adapters.out.persistence.transactions;

import com.alexandrebcruz.moneyflow.domain.model.Transaction;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(
        name = "transactions",
        indexes = {
                @Index(name = "idx_transactions_user_date", columnList = "user_id, occurred_at"),
                @Index(name = "idx_transactions_category", columnList = "category_id")
        }
)
public class TransactionEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "category_id", nullable = false)
    private UUID categoryId;

    @Column(name = "amount_cents", nullable = false)
    private int amountCents;


    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(columnDefinition = "transaction_type")
    @Enumerated(EnumType.STRING)
    private Transaction.TransactionType type;

    @Column(length = 255)
    private String description;

    @Column(name = "occurred_at", nullable = false)
    private Instant occurredAt;

    protected TransactionEntity() {
        // JPA only
    }

    public TransactionEntity(
            UUID userId,
            UUID categoryId,
            int amountCents,
            Transaction.TransactionType type,
            String description,
            Instant occurredAt
    ) {
        this.userId = userId;
        this.categoryId = categoryId;
        this.amountCents = amountCents;
        this.type = type;
        this.description = description;
        this.occurredAt = occurredAt;
    }

    /* =====================
       Getters
       ===================== */

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public int getAmountCents() {
        return amountCents;
    }

    public Transaction.TransactionType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public Instant getOccurredAt() {
        return occurredAt;
    }
}