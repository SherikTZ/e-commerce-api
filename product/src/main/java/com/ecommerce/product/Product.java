package com.ecommerce.product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "products")
public class Product {

    private final long DESC_MAX = 65535;
    private final long NAME_MAX = 255;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank
    @Max(NAME_MAX)
    private String name;

    @Max(DESC_MAX)
    private String description;

    @NotNull
    @Positive
    private Float price;

    @NotNull
    @PositiveOrZero
    private Integer stock;

    @NotBlank
    private String category;

    private Boolean isActive;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(@NotBlank @Max(NAME_MAX) String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(@Max(DESC_MAX) String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(@NotNull @Positive Float price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(@NotNull @PositiveOrZero Integer stock) {
        this.stock = stock;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(@NotBlank String category) {
        this.category = category;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}