package org.msvc.product_managment.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.msvc.product_managment.controllers.exceptions.CustomBadRequestException;
import org.msvc.product_managment.controllers.exceptions.ProductNotFoundException;
import org.msvc.product_managment.model.ProductManagment;
import org.msvc.product_managment.model.dtos.ProductManagmentResponse;
import org.msvc.product_managment.service.ProductManagmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.msvc.product_managment.commons.ProductManagmentConstraits.*;

@ExtendWith(MockitoExtension.class)
public class ProductManagmentControllerTest {

    @InjectMocks
    private ProductManagmentController productManagmentController;

    @Mock
    ProductManagmentService productManagmentService;

    @Test
    public void getAllProducts_ReturnsProductManagmentList() {
        when(productManagmentService.getAllProductsManagment()).thenReturn(PRODUCT_MANAGMENT_RESPONSE_LIST);

        ResponseEntity<List<ProductManagmentResponse>> productManagmentResponseList = productManagmentController.listAllProducts();

        assertEquals(HttpStatus.OK, productManagmentResponseList.getStatusCode());
        assertEquals(PRODUCT_MANAGMENT_RESPONSE_LIST, productManagmentResponseList.getBody());
        assertTrue(!productManagmentResponseList.getBody().isEmpty());
    }

    @Test
    public void getProductById_ReturnsProductManagmentResponse() {
        when(productManagmentService.getProductManagmentById(1L)).thenReturn(Optional.of(PRODUCT_MANAGMENT_RESPONSE));

        ResponseEntity<ProductManagmentResponse> productManagmentResponse = productManagmentController.getProductById(1L);

        assertEquals(HttpStatus.OK, productManagmentResponse.getStatusCode());
        assertEquals(PRODUCT_MANAGMENT_RESPONSE, productManagmentResponse.getBody());
    }

    @Test
    public void getProductById_ReturnsProductNotFound() {
        when(productManagmentService.getProductManagmentById(1L)).thenThrow(ProductNotFoundException.class);

        assertThatThrownBy(()-> productManagmentController.getProductById(1L))
                .isInstanceOf(ProductNotFoundException.class);
    }

    @Test
    public void createProduct_ReturnsProductManagmentResponse() {
        when(productManagmentService.createProductManagment(PRODUCT_MANAGMENT_REQUEST)).thenReturn(PRODUCT_MANAGMENT_RESPONSE);

        ResponseEntity<ProductManagmentResponse> productManagmentResponse = productManagmentController.createProduct(PRODUCT_MANAGMENT_REQUEST);

        assertEquals(HttpStatus.CREATED, productManagmentResponse.getStatusCode());
        assertEquals(PRODUCT_MANAGMENT_RESPONSE, productManagmentResponse.getBody());
    }

    @Test
    public void createProduct_WithNullData_ReturnsProductNotFound() {
        when(productManagmentService.createProductManagment(PRODUCT_MANAGMENT_REQUEST_WITH_NULL_PRODUCT))
                .thenThrow(ProductNotFoundException.class);

        assertThatThrownBy(()-> productManagmentController.createProduct(PRODUCT_MANAGMENT_REQUEST_WITH_NULL_PRODUCT))
                .isInstanceOf(ProductNotFoundException.class);
    }

    @Test
    public void updateProduct_ReturnsProductManagmentResponse() {
        when(productManagmentService.updateProductManagment(PRODUCT_MANAGMENT_REQUEST, 1l)).thenReturn(PRODUCT_MANAGMENT_RESPONSE);

        ResponseEntity<ProductManagmentResponse> productManagmentResponseUpdated = productManagmentController.updateProduct(PRODUCT_MANAGMENT_REQUEST, 1L);

        assertEquals(HttpStatus.OK, productManagmentResponseUpdated.getStatusCode());
        assertEquals(PRODUCT_MANAGMENT_RESPONSE, productManagmentResponseUpdated.getBody());
        assertNotNull(productManagmentResponseUpdated);
    }

    @Test
    public void updateProduct_WithInvalidData_ReturnsProductNotFound() {
        when(productManagmentService.updateProductManagment(PRODUCT_MANAGMENT_REQUEST_WITH_NULL_PRODUCT, 1L))
                .thenThrow(ProductNotFoundException.class);

        assertThatThrownBy(()-> productManagmentController.updateProduct(PRODUCT_MANAGMENT_REQUEST_WITH_NULL_PRODUCT, 1L))
                .isInstanceOf(ProductNotFoundException.class);
    }

    @Test
    public void deleteProduct_WithValidId_ReturnsNoContent() {
        productManagmentController.deleteProduct(1L);

        verify(productManagmentService).deleteProduct(1L);
    }

    @Test
    public void deleteProduct_WithInvalidId_ReturnsProductNotFound() {
        doThrow(CustomBadRequestException.class).when(productManagmentService).deleteProduct(1L);

        assertThatThrownBy(()-> productManagmentController.deleteProduct(1L)).isInstanceOf(CustomBadRequestException.class);
    }

    @Test
    public void filterProductByPrice_ReturnsProductManagmentResponseList() {
        when(productManagmentService.filterProductByPrice(100, 300)).thenReturn(PRODUCT_MANAGMENT_RESPONSE_LIST);

        ResponseEntity<List<ProductManagmentResponse>> filteredProducts = productManagmentController.filterByPrice(100, 300);

        assertEquals(HttpStatus.OK, filteredProducts.getStatusCode());
        assertEquals(PRODUCT_MANAGMENT_RESPONSE_LIST, filteredProducts.getBody());
        assertTrue(!filteredProducts.getBody().isEmpty());
        assertEquals(PRODUCT_MANAGMENT_RESPONSE_LIST.size(), filteredProducts.getBody().size());
    }
}
