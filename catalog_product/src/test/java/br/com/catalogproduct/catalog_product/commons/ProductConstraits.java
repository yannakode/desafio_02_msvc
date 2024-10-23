package br.com.catalogproduct.catalog_product.commons;

import br.com.catalogproduct.catalog_product.domain.dto.ProductRequest;
import br.com.catalogproduct.catalog_product.domain.dto.ProductResponse;
import br.com.catalogproduct.catalog_product.domain.model.Product;

import java.util.Arrays;
import java.util.List;

public class ProductConstraits {

    public static final Product PRODUCT = new Product(2L, "Computador", "New", 12.0);
    public static final ProductResponse PRODUCT_RESPONSE = new ProductResponse("Computador", "New", 12.0);
    public static final ProductRequest PRODUCT_REQUEST = new ProductRequest("Computador", "New", 12.0);

    public static final ProductRequest PRODUCT_REQUEST_INVALID_NAME_REQUEST = new ProductRequest("", "New", 12.0);
    public static final ProductRequest PRODUCT_REQUEST_INVALID_DESCRIPTION_REQUEST = new ProductRequest("Computador", "", 12.0);
    public static final ProductRequest PRODUCT_REQUEST_NULL_NAME_REQUEST = new ProductRequest(null, "New", 12.0);
    public static final ProductRequest PRODUCT_REQUEST_NULL_PRICE_REQUEST = new ProductRequest("Computador", "New", 0.0);
    public static final ProductRequest PRODUCT_REQUEST_NULL = new ProductRequest(null, null, 0.0);

    public static final List<Product> PRODUCTS = Arrays.asList(
            new Product(1L, "Product A", "Description A", 100.0),
            new Product(2L, "Product B", "Description B", 200.0),
            new Product(3L, "Product C", "Description C", 300.0),
            new Product(4L, "Product D", "Description D", 1000.0)
    );

    public static final List<ProductResponse> PRODUCT_RESPONSE_LIST = Arrays.asList(
            new ProductResponse("Product A", "Description A", 100.0),
            new ProductResponse("Product B", "Description B", 200.0),
            new ProductResponse("Product C", "Description C", 300.0),
            new ProductResponse("Product D", "Description D", 1000.0)
    );
}
