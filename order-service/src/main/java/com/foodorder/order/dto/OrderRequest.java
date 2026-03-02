package com.foodorder.order.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderRequest {

    @NotNull
    private Long userId;

    @NotNull
    private Long restaurantId;

    @NotBlank
    private String foodItems;

    @NotNull
    private Double totalAmount;
}