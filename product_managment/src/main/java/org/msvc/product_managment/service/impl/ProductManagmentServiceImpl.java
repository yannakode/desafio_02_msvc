package org.msvc.product_managment.service.impl;

import feign.FeignException;
import org.modelmapper.ModelMapper;
import org.msvc.product_managment.clients.ProductFeignClient;
import org.msvc.product_managment.controllers.exceptions.ProductNotFoundException;
import org.msvc.product_managment.model.Product;
import org.msvc.product_managment.model.ProductManagment;
import org.msvc.product_managment.model.dtos.ProductManagmentRequest;
import org.msvc.product_managment.model.dtos.ProductManagmentResponse;
import org.msvc.product_managment.service.ProductManagmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ProductManagmentServiceImpl implements ProductManagmentService {

    private final ProductFeignClient productFeignClient;

    private final ModelMapper modelMapper;

    public ProductManagmentServiceImpl(ProductFeignClient productFeignClient, ModelMapper modelMapper) {
        this.productFeignClient = productFeignClient;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProductManagmentResponse> getAllProductsManagment() {
        return productFeignClient.findAll()
                .stream()
                .map(p -> new ProductManagment(p, new Random().nextInt(10) + 1))
                .map(productManagment -> modelMapper.map(productManagment, ProductManagmentResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductManagmentResponse> getProductManagmentById(Long id) {
        try {
            Product product = productFeignClient.findById(id);
            ProductManagment productManagment = new ProductManagment(product, new Random().nextInt(10) + 1);
            return Optional.ofNullable(modelMapper.map(productManagment, ProductManagmentResponse.class));
        } catch (FeignException.NotFound e) {
            throw new ProductNotFoundException(id);
        }
    }

    
}
