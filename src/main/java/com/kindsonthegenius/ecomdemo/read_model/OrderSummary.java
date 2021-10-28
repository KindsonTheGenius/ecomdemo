package com.kindsonthegenius.ecomdemo.read_model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class OrderSummary {

    @Id
    private UUID id;
    private Double price;
    private Integer number;

    @ManyToOne
    @JoinColumn(name = "productid", insertable = false, updatable = false)
    private ProductSummary summary;

    private String productid;

    public OrderSummary(UUID id, Double price, Integer number,String productid) {
        this.id = id;
        this.price = price;
        this.number = number;
        this.productid = productid;
    }
}
