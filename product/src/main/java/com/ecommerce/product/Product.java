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
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
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

}