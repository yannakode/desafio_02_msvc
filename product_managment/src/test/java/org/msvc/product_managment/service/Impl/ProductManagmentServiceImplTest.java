package org.msvc.product_managment.service.Impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.msvc.product_managment.commons.ProductConstraits.*;
import static org.msvc.product_managment.commons.ProductManagmentConstraits.*;

@ExtendWith(MockitoExtension.class)
public class ProductManagmentServiceImplTest {

    @InjectMocks
    private ProductManagmentServiceImpl productManagmentService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private ProductFeignClient productFeignClient;

    @Test
    public void getAllProducts_ReturnsProductManagmentList(){
        when(productFeignClient.findAll()).thenReturn(PRODUCTS);
        when(modelMapper.map(any(ProductManagment.class), eq(ProductManagmentResponse.class)))
                .thenReturn(PRODUCT_MANAGMENT_RESPONSE);

        List<ProductManagmentResponse> productResponseList = productManagmentService.getAllProductsManagment();

        verify(productFeignClient, times(1)).findAll();
        assertNotNull(productResponseList);
        verify(modelMapper, times(PRODUCTS.size())).map(any(ProductManagment.class), eq(ProductManagmentResponse.class));
    }

    @Test
    public void getProductById_ByValidId_ReturnsProductManagmentResponse(){
        when(productFeignClient.findById(1L)).thenReturn(PRODUCT);
        when(modelMapper.map(any(ProductManagment.class), eq(ProductManagmentResponse.class))).thenReturn(PRODUCT_MANAGMENT_RESPONSE);

        Optional<ProductManagmentResponse> productManagmentResponse = productManagmentService.getProductManagmentById(1L);

        assertNotNull(productManagmentResponse);
        verify(productFeignClient, times(1)).findById(1L);
        assertEquals(productManagmentResponse.get(), PRODUCT_MANAGMENT_RESPONSE);
    }

    @Test
    public void getProductById_ByInvalidId_ThrowsException(){
        when(productFeignClient.findById(1L)).thenThrow(ProductNotFoundException.class);

        assertThatThrownBy(()-> productManagmentService.getProductManagmentById(1L)).isInstanceOf(ProductNotFoundException.class);
        verify(productFeignClient, times(1)).findById(1L);
    }

    @Test
    public void createProduct_WithValidData_ReturnsProductManagmentResponse(){
        when(modelMapper.map(any(Product.class), eq(ProductRequest.class))).thenReturn(PRODUCT_REQUEST);
        when(productFeignClient.save(PRODUCT_REQUEST)).thenReturn(PRODUCT_RESPONSE);
        when(modelMapper.map(any(ProductManagmentRequest.class), eq(ProductManagmentResponse.class))).thenReturn(PRODUCT_MANAGMENT_RESPONSE);

        ProductManagmentResponse productManagmentResponse = productManagmentService.createProductManagment(PRODUCT_MANAGMENT_REQUEST);

        verify(productFeignClient, times(1)).save(PRODUCT_REQUEST);
        assertNotNull(productManagmentResponse);
        assertEquals(productManagmentResponse, PRODUCT_MANAGMENT_RESPONSE);
    }

    @Test
    public void createProductManagment_WithNullProduct_ThrowsException(){
        assertThatThrownBy(()-> productManagmentService.createProductManagment(PRODUCT_MANAGMENT_REQUEST_WITH_NULL_PRODUCT))
                .isInstanceOf(ProductNotFoundException.class);
    }

    @Test
    public void updateProduct_WithValidData_ReturnsProductManagmentResponse(){
        when(modelMapper.map(any(Product.class), eq(ProductRequest.class))).thenReturn(PRODUCT_REQUEST);
        when(productFeignClient.update(PRODUCT_REQUEST, 1L)).thenReturn(PRODUCT_RESPONSE);
        when(modelMapper.map(any(ProductResponse.class), eq(Product.class))).thenReturn(PRODUCT);
        when(modelMapper.map(any(ProductManagmentRequest.class), eq(ProductManagmentResponse.class))).thenReturn(PRODUCT_MANAGMENT_RESPONSE);

        ProductManagmentResponse productManagmentResponse = productManagmentService.updateProductManagment(PRODUCT_MANAGMENT_REQUEST, 1L);
        ProductManagment productManagmentUpdated = new ProductManagment(PRODUCT, 1);

        verify(productFeignClient, times(1)).update(PRODUCT_REQUEST, 1L);
        assertNotNull(productManagmentUpdated);
    }

    @Test
    public void updateProductManagment_WithNullProduct_ThrowsException(){
        assertThatThrownBy(()-> productManagmentService.updateProductManagment(PRODUCT_MANAGMENT_REQUEST_WITH_NULL_PRODUCT, 1L))
                .isInstanceOf(ProductNotFoundException.class);
    }
}
