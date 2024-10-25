package org.msvc.product_managment.controllers;

import org.msvc.product_managment.model.ProductManagment;
import org.msvc.product_managment.model.dtos.ProductManagmentRequest;
import org.msvc.product_managment.model.dtos.ProductManagmentResponse;
import org.msvc.product_managment.service.ProductManagmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("productInventory/productManagement/v1")
public class ProductManagmentController {

    private final ProductManagmentService productManagmentService;

    public ProductManagmentController(ProductManagmentService productManagmentService) {
        this.productManagmentService = productManagmentService;
    }

    @GetMapping
    public ResponseEntity<List<ProductManagmentResponse>> listAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productManagmentService.getAllProductsManagment());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductManagmentResponse> getProductById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(productManagmentService.getProductManagmentById(id).get());
    }

    @PostMapping
    public ResponseEntity<ProductManagmentResponse> createProduct(@RequestBody ProductManagmentRequest productManagmentRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productManagmentService.createProductManagment(productManagmentRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductManagmentResponse> updateProduct(@RequestBody ProductManagmentRequest productManagmentRequest, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(productManagmentService.updateProductManagment(productManagmentRequest, id));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productManagmentService.deleteProduct(id);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductManagmentResponse>> filterByPrice(@RequestParam(required = false) Integer min, @RequestParam(required = false) Integer max) {
        return ResponseEntity.status(HttpStatus.OK).body(productManagmentService.filterProductByPrice(min, max));
    }
}
