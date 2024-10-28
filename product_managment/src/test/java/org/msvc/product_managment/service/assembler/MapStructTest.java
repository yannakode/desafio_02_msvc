package org.msvc.product_managment.service.assembler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.msvc.product_managment.model.Product;
import org.msvc.product_managment.model.ProductManagment;
import org.msvc.product_managment.model.dtos.ProductManagmentResponse;
import org.msvc.product_managment.model.dtos.ProductRequest;
import org.msvc.product_managment.model.dtos.ProductResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.msvc.product_managment.commons.ProductConstraits.*;
import static org.msvc.product_managment.commons.ProductManagmentConstraits.*;

@ExtendWith(MockitoExtension.class)
public class MapStructTest {

    @InjectMocks
    MapStruct mapStruct = mock(MapStruct.class);

    @BeforeEach
    void setup(){
        mapStruct = Mappers.getMapper(MapStruct.class);
    }

    @Test
    public void toProductResponseToProduct_ReturnsProduct() {
        Product product = mapStruct.toProduct(PRODUCT_RESPONSE);

        assertEquals(product, PRODUCT);
    }

    @Test
    public void productToProductResponse_ReturnsProductResponse() {
        ProductResponse productResponse = mapStruct.toProductResponse(PRODUCT);

        assertEquals(productResponse, PRODUCT_RESPONSE);
    }

    @Test
    public void productResponseToProductRequest_ReturnsProductRequest() {
        ProductRequest productRequest = mapStruct.toProductRequest(PRODUCT_RESPONSE);

        assertEquals(productRequest, PRODUCT_REQUEST);
    }

    @Test
    public void productToProductRequest_ReturnsProductRequest() {
        ProductRequest productRequest = mapStruct.toProductRequest(PRODUCT);

        assertEquals(productRequest, PRODUCT_REQUEST);
    }

    @Test
    public void productManagmentResponseToProductManagment_ReturnsProductManagment() {
        ProductManagment productManagment = mapStruct.toProductManagment(PRODUCT_MANAGMENT_RESPONSE);

        assertEquals(productManagment, PRODUCT_MANAGMENT);
    }

    @Test
    public void productManagmentToProductManagmentResponse_ReturnsProductManagmentResponse() {
        ProductManagmentResponse productManagmentResponse = mapStruct.toProductManagmentResponse(PRODUCT_MANAGMENT);

        assertEquals(productManagmentResponse, PRODUCT_MANAGMENT_RESPONSE);
    }

    @Test
    public void productManagmentRequestToProductManagmentResponse_ReturnsProductManagmentResponse() {
        ProductManagmentResponse productManagmentResponse = mapStruct.toProductManagmentResponse(PRODUCT_MANAGMENT_REQUEST);

        assertEquals(productManagmentResponse, PRODUCT_MANAGMENT_RESPONSE);
    }
}
