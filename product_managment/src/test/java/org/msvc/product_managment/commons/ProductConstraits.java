package org.msvc.product_managment.commons;

import org.msvc.product_managment.model.Product;
import org.msvc.product_managment.model.dtos.ProductRequest;
import org.msvc.product_managment.model.dtos.ProductResponse;

import java.util.Arrays;
import java.util.List;

public class ProductConstraits {
    public static final Product PRODUCT = new Product("Laptop", "Gaming laptop", 1200.0);
    public static final ProductRequest PRODUCT_REQUEST = new ProductRequest("Laptop", "Gaming laptop", 1200.0);
    public static final ProductResponse PRODUCT_RESPONSE = new ProductResponse("Laptop", "Gaming laptop", 1200.0);

    public static final ProductRequest PRODUCT_REQUEST_NULL = new ProductRequest(null, null, 0.0);

    public static final List<Product> PRODUCTS = Arrays.asList(
            new Product("Product A", "Description A", 100.0),
            new Product("Product B", "Description B", 200.0),
            new Product("Product C", "Description C", 300.0),
            new Product("Product D", "Description D", 1000.0)
    );
}
