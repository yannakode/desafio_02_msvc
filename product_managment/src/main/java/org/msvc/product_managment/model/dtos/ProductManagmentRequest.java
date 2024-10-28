package org.msvc.product_managment.model.dtos;

import org.msvc.product_managment.model.Product;

public record ProductManagmentRequest(Product product, Integer quantity) {
}
