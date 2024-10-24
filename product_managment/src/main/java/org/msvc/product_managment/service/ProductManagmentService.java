package org.msvc.product_managment.service;

import org.msvc.product_managment.model.ProductManagment;
import org.msvc.product_managment.model.dtos.ProductManagmentResponse;

import java.util.List;
import java.util.Optional;

public interface ProductManagmentService {

    List<ProductManagmentResponse> getAllProductsManagment();

    Optional<ProductManagmentResponse> getProductManagmentById(Long id);
}
