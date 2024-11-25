package org.msvc.product_managment.model.dtos;

import jakarta.validation.Valid;
import org.msvc.product_managment.model.Product;

import jakarta.validation.constraints.NotNull;

public record ProductManagmentRequest(
        @Valid @NotNull(message = "Product cannot be null") Product product,
        @NotNull(message = "Quantity cannot be null") Integer quantity
) {
}

