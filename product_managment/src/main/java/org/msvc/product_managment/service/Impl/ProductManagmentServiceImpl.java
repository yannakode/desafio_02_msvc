package org.msvc.product_managment.service.Impl;

import feign.FeignException;
import org.modelmapper.ModelMapper;
import org.msvc.product_managment.clients.ProductFeignClient;
import org.msvc.product_managment.controllers.exceptions.CustomBadRequestException;
import org.msvc.product_managment.controllers.exceptions.ProductNotFoundException;
import org.msvc.product_managment.model.Product;
import org.msvc.product_managment.model.ProductManagment;
import org.msvc.product_managment.model.dtos.ProductManagmentRequest;
import org.msvc.product_managment.model.dtos.ProductManagmentResponse;
import org.msvc.product_managment.model.dtos.ProductRequest;
import org.msvc.product_managment.model.dtos.ProductResponse;
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
                .map(p-> modelMapper.map(p, ProductManagmentResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductManagmentResponse> getProductManagmentById(Long id) {
        try {
            Product product = productFeignClient.findById(id);
            ProductManagment productManagment = new ProductManagment();
            productManagment.setProduct(product);
            if(productManagment.getQuantity() == null) productManagment.setQuantity(new Random().nextInt(10) + 1);
            return Optional.ofNullable(modelMapper.map(productManagment, ProductManagmentResponse.class));
        } catch (FeignException.NotFound e) {
            throw new ProductNotFoundException(id);
        }
    }

    @Override
    public ProductManagmentResponse createProductManagment(ProductManagmentRequest productManagmentRequest) {
        if(productManagmentRequest.getProduct() == null) throw new ProductNotFoundException("Product cannot be null");

        ProductRequest productRequest = modelMapper.map(productManagmentRequest.getProduct(), ProductRequest.class);
        try{
            productFeignClient.save(productRequest);

            return modelMapper.map(productManagmentRequest, ProductManagmentResponse.class);
        }catch (FeignException.BadRequest e){
            throw new CustomBadRequestException(e);
        }
    }

    @Override
    public ProductManagmentResponse updateProductManagment(ProductManagmentRequest productManagmentRequest, Long productId) {
        if(productManagmentRequest.getProduct() == null) throw new ProductNotFoundException("Product cannot be null");

        try{
            ProductRequest productRequest = modelMapper.map(productManagmentRequest.getProduct(), ProductRequest.class);
            ProductResponse productResponse = productFeignClient.update(productRequest, productId);
            Product productUpated = modelMapper.map(productResponse, Product.class);

            ProductManagmentRequest productManagmentRequestUpdated = new ProductManagmentRequest();
            productManagmentRequestUpdated.setProduct(productUpated);
            productManagmentRequestUpdated.setQuantity(productManagmentRequest.getQuantity());

            return modelMapper.map(productManagmentRequestUpdated, ProductManagmentResponse.class);
        } catch (FeignException.BadRequest e) {
            throw new CustomBadRequestException(e);
        }
    }
}
