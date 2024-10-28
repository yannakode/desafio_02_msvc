package org.msvc.product_managment.commons;

import org.msvc.product_managment.model.Product;
import org.msvc.product_managment.model.ProductManagment;
import org.msvc.product_managment.model.dtos.ProductManagmentRequest;
import org.msvc.product_managment.model.dtos.ProductManagmentResponse;

import java.util.Arrays;
import java.util.List;

import static org.msvc.product_managment.commons.ProductConstraits.PRODUCT;

public class ProductManagmentConstraits {
    public static final ProductManagment PRODUCT_MANAGMENT = new ProductManagment(PRODUCT, 5);
    public static final ProductManagment PRODUCT_MANAGMENT_WITH_NULL_PRODUCT = new ProductManagment(null, 5);
    public static final ProductManagment PRODUCT_MANAGMENT_WITH_NULL_QUANTITY = new ProductManagment(PRODUCT, null);
    public static final ProductManagment PRODUCT_MANAGMENT_WITH_NEGATIVE_QUANTITY = new ProductManagment(PRODUCT, -5);

    public static final List<ProductManagment> PRODUCT_MANAGMENT_LIST = Arrays.asList(
            new ProductManagment(new Product("Product A", "Description A", 100.0), 10),
            new ProductManagment(new Product("Product B", "Description B", 200.0), 20),
            new ProductManagment(new Product("Product C", "Description C", 300.0), 30)
    );

    public static final ProductManagmentResponse PRODUCT_MANAGMENT_RESPONSE = new ProductManagmentResponse(PRODUCT, 5);
    public static final ProductManagmentResponse PRODUCT_MANAGMENT_RESPONSE_WITH_NULL_PRODUCT = new ProductManagmentResponse(null, 5);
    public static final ProductManagmentResponse PRODUCT_MANAGMENT_RESPONSE_WITH_NULL_QUANTITY = new ProductManagmentResponse(PRODUCT, null);
    public static final ProductManagmentResponse PRODUCT_MANAGMENT_RESPONSE_WITH_NEGATIVE_QUANTITY = new ProductManagmentResponse(PRODUCT, -5);

    public static final List<ProductManagmentResponse> PRODUCT_MANAGMENT_RESPONSE_LIST = Arrays.asList(
            new ProductManagmentResponse(new Product("Product A", "Description A", 100.0), 10),
            new ProductManagmentResponse(new Product("Product B", "Description B", 200.0), 20),
            new ProductManagmentResponse(new Product("Product C", "Description C", 300.0), 30)
    );

    public static final ProductManagmentRequest PRODUCT_MANAGMENT_REQUEST = new ProductManagmentRequest(PRODUCT, 5);
    public static final ProductManagmentRequest PRODUCT_MANAGMENT_REQUEST_WITH_NULL_PRODUCT = new ProductManagmentRequest(null, 5);

    public static final List<ProductManagmentRequest> PRODUCT_MANAGMENT_REQUEST_LIST = Arrays.asList(
            new ProductManagmentRequest(new Product("Product A", "Description A", 100.0), 10),
            new ProductManagmentRequest(new Product("Product B", "Description B", 200.0), 20),
            new ProductManagmentRequest(new Product("Product C", "Description C", 300.0), 30)
    );
}
