package com.kaycrm.kaycrm.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderDTO {
    private Long id;

    @NotNull
    private String product;

    @NotNull
    private Double price;

    @NotNull
    private Long customerId;

    // Constructor
    public OrderDTO(Long id, String product, Double price, Long customerId) {
        this.id = id;
        this.product = product;
        this.price = price;
        this.customerId = customerId;
    }

    // Default constructor
    public OrderDTO() {
    }
}