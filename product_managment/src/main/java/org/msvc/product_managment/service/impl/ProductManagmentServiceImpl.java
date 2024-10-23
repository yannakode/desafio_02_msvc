package org.msvc.product_managment.service.impl;

import org.msvc.product_managment.clients.ProductFeignClient;
import org.msvc.product_managment.model.Product;
import org.msvc.product_managment.model.ProductManagment;
import org.msvc.product_managment.service.ProductManagmentService;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

public class ProductManagmentServiceImpl implements ProductManagmentService {

    private final ProductFeignClient productFeignClient;

    public ProductManagmentServiceImpl(ProductFeignClient productFeignClient) {
        this.productFeignClient = productFeignClient;
    }

    @Override
    public List<ProductManagment> getAllProductsManagment() {
        return productFeignClient.findAll()
                .stream()
                .map(p -> new ProductManagment(p, new Random().nextInt(10) + 1))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductManagment> getProductManagmentById(Long id) {
        Product product = productFeignClient.findById(id);
        return Optional.of(new ProductManagment(product, new Random().nextInt(10) + 1));
    }
}
