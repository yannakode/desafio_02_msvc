package org.msvc.product_managment.service.Impl;

import feign.FeignException;
import org.msvc.product_managment.clients.ProductFeignClient;
import org.msvc.product_managment.service.exceptions.CustomBadRequestException;
import org.msvc.product_managment.service.exceptions.ProductNotFoundException;
import org.msvc.product_managment.model.Product;
import org.msvc.product_managment.model.ProductManagment;
import org.msvc.product_managment.model.dtos.ProductManagmentRequest;
import org.msvc.product_managment.model.dtos.ProductManagmentResponse;
import org.msvc.product_managment.model.dtos.ProductRequest;
import org.msvc.product_managment.service.ProductManagmentService;
import org.msvc.product_managment.service.assembler.MapStruct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ProductManagmentServiceImpl implements ProductManagmentService {

    private final ProductFeignClient productFeignClient;

    private final MapStruct mapStruct;

    private final Integer random = new Random().nextInt(10) + 1;

    public ProductManagmentServiceImpl(ProductFeignClient productFeignClient, MapStruct mapStruct) {
        this.productFeignClient = productFeignClient;
        this.mapStruct = mapStruct;
    }

    @Override
    public List<ProductManagmentResponse> getAllProductsManagment() {
        return productFeignClient.findAll()
                .stream()
                .map(p -> new ProductManagment(p, new Random().nextInt(10) + 1))
                .map(mapStruct::toProductManagmentResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductManagmentResponse> getProductManagmentById(Long id) {
        try {
            Product product = productFeignClient.findById(id);
            ProductManagment productManagment = new ProductManagment();
            productManagment.setProduct(product);
            if(productManagment.getQuantity() == null) productManagment.setQuantity(new Random().nextInt(10) + 1);
            return Optional.ofNullable(mapStruct.toProductManagmentResponse(productManagment));
        } catch (FeignException.NotFound e) {
            throw new ProductNotFoundException(id);
        }
    }

    @Override
    public ProductManagmentResponse createProductManagment(ProductManagmentRequest productManagmentRequest) {
        if(productManagmentRequest.product() == null) throw new ProductNotFoundException("Product cannot be null");

        ProductRequest productRequest = mapStruct.toProductRequest(productManagmentRequest.product());
        try{
            Product productResponse = productFeignClient.save(productRequest);

            ProductManagment productManagment = new ProductManagment(productResponse, productManagmentRequest.quantity());

            return mapStruct.toProductManagmentResponse(productManagment);
        }catch (FeignException.BadRequest e){
            throw new CustomBadRequestException(e);
        }
    }

    @Override
    public ProductManagmentResponse updateProductManagment(ProductManagmentRequest productManagmentRequest, Long productId) {
        if(productManagmentRequest.product() == null) throw new ProductNotFoundException("Product cannot be null");

        ProductRequest productRequest = mapStruct.toProductRequest(productManagmentRequest.product());
        try{
            Product productUpdated = productFeignClient.update(productRequest, productId);

            ProductManagmentRequest productManagmentRequestUpdated = new ProductManagmentRequest(productUpdated, productManagmentRequest.quantity());

            return mapStruct.toProductManagmentResponse(productManagmentRequestUpdated);
        } catch (FeignException.BadRequest e) {
            throw new CustomBadRequestException(e);
        }
    }

    @Override
    public void deleteProduct(Long id){
        try{
            productFeignClient.delete(id);
        }catch (FeignException.NotFound e){
            throw new CustomBadRequestException("Product not found with id " + id);
        }
    }

    @Override
    public List<ProductManagmentResponse> filterProductByPrice(Integer min, Integer max){
        return productFeignClient.filterByPrice(min, max)
                .stream()
                .map(p -> new ProductManagment(p, new Random().nextInt(10) + 1))
                .map(mapStruct::toProductManagmentResponse)
                .collect(Collectors.toList());
    }
}
