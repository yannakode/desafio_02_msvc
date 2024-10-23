package org.msvc.product_managment.service;

import org.msvc.product_managment.model.ProductManagment;

import java.util.List;
import java.util.Optional;

public interface ProductManagmentService {

    List<ProductManagment> getAllProductsManagment();

    Optional<ProductManagment> getProductManagmentById(Long id);
}
