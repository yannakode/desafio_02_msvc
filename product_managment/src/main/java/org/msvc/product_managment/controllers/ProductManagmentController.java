package org.msvc.product_managment.controllers;

import org.msvc.product_managment.model.ProductManagment;
import org.msvc.product_managment.model.dtos.ProductManagmentResponse;
import org.msvc.product_managment.service.ProductManagmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductManagmentController {

    private final ProductManagmentService productManagmentService;

    public ProductManagmentController(ProductManagmentService productManagmentService) {
        this.productManagmentService = productManagmentService;
    }

    @GetMapping
    public List<ProductManagmentResponse> listAllProducts() {
        return productManagmentService.getAllProductsManagment();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductManagmentResponse> getProductById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(productManagmentService.getProductManagmentById(id).get());
    }
}
