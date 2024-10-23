package br.com.catalogproduct.catalog_product.adapters.in.rest.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long productId) {
        super("Product not found with id " + productId);
    }
}
