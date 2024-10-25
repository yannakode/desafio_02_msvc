package org.msvc.product_managment.service;

import org.msvc.product_managment.model.dtos.ProductManagmentRequest;
import org.msvc.product_managment.model.dtos.ProductManagmentResponse;

import java.util.List;
import java.util.Optional;

public interface ProductManagmentService {

    List<ProductManagmentResponse> getAllProductsManagment();

    Optional<ProductManagmentResponse> getProductManagmentById(Long id);

    ProductManagmentResponse createProductManagment(ProductManagmentRequest productManagmentRequest);

    ProductManagmentResponse updateProductManagment(ProductManagmentRequest productManagmentRequest, Long id);

    void deleteProduct(Long id);

    List<ProductManagmentResponse> filterProductByPrice(Integer min, Integer max);
}
