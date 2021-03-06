package com.kindsonthegenius.ecomdemo.read_model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class ProductSummary {

    @Id
    private String id;
    private Double price;
    private Integer stock;
    private String description;

    public ProductSummary(String id, Double price, Integer stock, String description) {
        this.id = id;
        this.price = price;
        this.stock = stock;
        this.description = description;
    }

}
