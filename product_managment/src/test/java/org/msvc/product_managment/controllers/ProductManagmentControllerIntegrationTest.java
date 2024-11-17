package org.msvc.product_managment.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.msvc.product_managment.service.exceptions.ProductNotFoundException;
import org.msvc.product_managment.service.ProductManagmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.msvc.product_managment.commons.ProductConstraits.PRODUCT;
import static org.msvc.product_managment.commons.ProductManagmentConstraits.*;
import static org.msvc.product_managment.commons.ProductManagmentConstraits.PRODUCT_MANAGMENT_REQUEST;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductManagmentController.class)
public class ProductManagmentControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductManagmentService productManagmentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAllProducts_ReturnsProductManagmentList() throws Exception {
        when(productManagmentService.getAllProductsManagment())
                .thenReturn(PRODUCT_MANAGMENT_RESPONSE_LIST);

        mockMvc.perform(get("/productInventory/productManagement/v1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void getProductById_ReturnsProductManagmentResponse() throws Exception {
        when(productManagmentService.getProductManagmentById(1L))
                .thenReturn(Optional.of(PRODUCT_MANAGMENT_RESPONSE));

        mockMvc.perform(get("/productInventory/productManagement/v1/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.product.name").value("Laptop"))
                .andExpect(jsonPath("$.product.description").value("Gaming laptop"))
                .andExpect(jsonPath("$.product.price").value(1200.0))
                .andExpect(jsonPath("$.quantity").value(5));
    }

    @Test
    public void getProductById_ReturnsProductNotFound() throws Exception {
        when(productManagmentService.getProductManagmentById(1L))
                .thenThrow(ProductNotFoundException.class);

        mockMvc.perform(get("/productInventory/productManagement/v1/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createProduct_ReturnsProductManagmentResponse() throws Exception {
        when(productManagmentService.createProductManagment(PRODUCT_MANAGMENT_REQUEST)).thenReturn(PRODUCT_MANAGMENT_RESPONSE);

        mockMvc.perform(post("/productInventory/productManagement/v1")
                        .content(objectMapper.writeValueAsString(PRODUCT))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void createProduct_WithNullProduct_ReturnsProductNotFound() throws Exception {
        when(productManagmentService.createProductManagment(PRODUCT_MANAGMENT_REQUEST_WITH_NULL_PRODUCT))
                .thenThrow(ProductNotFoundException.class);

        mockMvc.perform(post("/productInventory/productManagement/v1")
                        .content(objectMapper.writeValueAsString(PRODUCT_MANAGMENT_REQUEST_WITH_NULL_PRODUCT))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }
}
