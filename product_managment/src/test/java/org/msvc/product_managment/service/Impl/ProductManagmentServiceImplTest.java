package org.msvc.product_managment.service.Impl;

import feign.FeignException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.msvc.product_managment.clients.ProductFeignClient;
import org.msvc.product_managment.service.exceptions.CustomBadRequestException;
import org.msvc.product_managment.service.exceptions.ProductNotFoundException;
import org.msvc.product_managment.model.Product;
import org.msvc.product_managment.model.ProductManagment;
import org.msvc.product_managment.model.dtos.ProductManagmentRequest;
import org.msvc.product_managment.model.dtos.ProductManagmentResponse;
import org.msvc.product_managment.service.assembler.MapStruct;

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
    MapStruct mapStruct;

    @Mock
    private ProductFeignClient productFeignClient;

    @Test
    public void getAllProducts_ReturnsProductManagmentList() {
        when(productFeignClient.findAll()).thenReturn(PRODUCTS);
        when(mapStruct.toProductManagmentResponse(any(ProductManagment.class))).thenReturn(PRODUCT_MANAGMENT_RESPONSE);

        List<ProductManagmentResponse> productResponseList = productManagmentService.getAllProductsManagment();

        verify(productFeignClient, times(1)).findAll();
        assertNotNull(productResponseList);
        assertEquals(PRODUCTS.size(), productResponseList.size());
        verify(mapStruct, times(PRODUCTS.size())).toProductManagmentResponse(any(ProductManagment.class));
    }

    @Test
    public void getProductById_ByValidId_ReturnsProductManagmentResponse(){
        when(productFeignClient.findById(1L)).thenReturn(PRODUCT);
        when(mapStruct.toProductManagmentResponse(any(ProductManagment.class))).thenReturn(PRODUCT_MANAGMENT_RESPONSE);

        Optional<ProductManagmentResponse> productManagmentResponse = productManagmentService.getProductManagmentById(1L);

        assertNotNull(productManagmentResponse);
        verify(productFeignClient, times(1)).findById(1L);
        assertEquals(productManagmentResponse.get(), PRODUCT_MANAGMENT_RESPONSE);
        verify(mapStruct, times(1)).toProductManagmentResponse(any(ProductManagment.class));
    }

    @Test
    public void getProductById_ByInvalidId_ThrowsException(){
        when(productFeignClient.findById(1L)).thenThrow(ProductNotFoundException.class);

        assertThatThrownBy(()-> productManagmentService.getProductManagmentById(1L)).isInstanceOf(ProductNotFoundException.class);
        verify(productFeignClient, times(1)).findById(1L);
    }

    @Test
    public void createProduct_WithValidData_ReturnsProductManagmentResponse(){
        when(mapStruct.toProductRequest(any(Product.class))).thenReturn(PRODUCT_REQUEST);
        when(productFeignClient.save(PRODUCT_REQUEST)).thenReturn(PRODUCT);
        when(mapStruct.toProductManagmentResponse(any(ProductManagment.class))).thenReturn(PRODUCT_MANAGMENT_RESPONSE);

        ProductManagmentResponse productManagmentResponse = productManagmentService.createProductManagment(PRODUCT_MANAGMENT_REQUEST);

        verify(productFeignClient, times(1)).save(PRODUCT_REQUEST);
        assertNotNull(productManagmentResponse);
        assertEquals(productManagmentResponse, PRODUCT_MANAGMENT_RESPONSE);
        verify(mapStruct, times(1)).toProductRequest(any(Product.class));
        verify(mapStruct, times(1)).toProductManagmentResponse(any(ProductManagment.class));
    }

    @Test
    public void createProductManagment_WithNullProduct_ThrowsException(){
        assertThatThrownBy(()-> productManagmentService.createProductManagment(PRODUCT_MANAGMENT_REQUEST_WITH_NULL_PRODUCT))
                .isInstanceOf(ProductNotFoundException.class);
    }

    @Test
    public void updateProduct_WithValidData_ReturnsProductManagmentResponse(){
        when(mapStruct.toProductRequest(any(Product.class))).thenReturn(PRODUCT_REQUEST);
        when(productFeignClient.update(PRODUCT_REQUEST, 1L)).thenReturn(PRODUCT);
        when(mapStruct.toProductManagmentResponse(any(ProductManagmentRequest.class))).thenReturn(PRODUCT_MANAGMENT_RESPONSE);

        ProductManagmentResponse productManagmentResponse = productManagmentService.updateProductManagment(PRODUCT_MANAGMENT_REQUEST, 1L);
        ProductManagment productManagmentUpdated = new ProductManagment(PRODUCT, 1);

        verify(productFeignClient, times(1)).update(PRODUCT_REQUEST, 1L);
        assertNotNull(productManagmentUpdated);
        verify(mapStruct, times(1)).toProductRequest(any(Product.class));
        verify(mapStruct, times(1)).toProductManagmentResponse(any(ProductManagmentRequest.class));
    }

    @Test
    public void updateProductManagment_WithNullProduct_ThrowsException(){
        assertThatThrownBy(()-> productManagmentService.updateProductManagment(PRODUCT_MANAGMENT_REQUEST_WITH_NULL_PRODUCT, 1L))
                .isInstanceOf(ProductNotFoundException.class);
    }

    @Test
    public void deleteProduct_WithValidData_ReturnsProductManagmentResponse(){
        productManagmentService.deleteProduct(1L);

        verify(productFeignClient, times(1)).delete(1L);
    }

    @Test
    public void deleteProductManagment_WithNullProduct_ThrowsException(){
        doThrow(FeignException.NotFound.class).when(productFeignClient).delete(1L);

        assertThatThrownBy(()-> productManagmentService.deleteProduct(1L))
                .isInstanceOf(CustomBadRequestException.class);
    }

    @Test
    public void filterProductsByPrice_ReturnsProductManagmentResponseList(){
        when(productFeignClient.filterByPrice(100, 600)).thenReturn(PRODUCTS);
        when(mapStruct.toProductManagmentResponse(any(ProductManagment.class)))
                .thenReturn(PRODUCT_MANAGMENT_RESPONSE);

        List<ProductManagmentResponse> filteredProducts = productManagmentService.filterProductByPrice(100, 600);

        assertNotNull(filteredProducts);
        verify(productFeignClient, times(1)).filterByPrice(100, 600);
        assertEquals(PRODUCTS.size(), filteredProducts.size());
        verify(mapStruct, times(PRODUCTS.size())).toProductManagmentResponse(any(ProductManagment.class));
    }
}
