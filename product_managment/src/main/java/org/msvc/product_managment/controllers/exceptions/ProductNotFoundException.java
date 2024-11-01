package org.msvc.product_managment.controllers.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long id) {
        super("Product with id " + id + " not found");
    }

    public ProductNotFoundException(String message) {
        super("Product cannot be null");
    }
}
