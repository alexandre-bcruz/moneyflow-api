package com.alexandrebcruz.moneyflow.adapters.out.persistence.category;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "categories", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "name"})
})
public class CategoryEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column( nullable = false, length = 50)
    private String name;

    protected  CategoryEntity() {}

    CategoryEntity(UUID id, UUID userId, String name){
        this.id = id;
        this.userId = userId;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }
}
