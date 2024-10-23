package br.com.catalogproduct.catalog_product.application.service;

import br.com.catalogproduct.catalog_product.application.ports.out.ApiProductsPortOut;
import br.com.catalogproduct.catalog_product.domain.dto.ProductResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static br.com.catalogproduct.catalog_product.commons.ProductConstraits.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductCrudPortOutImplTest {
    @InjectMocks
    ProductCrudUseCaseImpl productCrudUseCase;

    @Mock
    ApiProductsPortOut apiProductsPortOut;

    @Test
    public void createProduct_ReturnsProductResponse() {
        when(apiProductsPortOut.create(PRODUCT_REQUEST)).thenReturn(PRODUCT_RESPONSE);

        ProductResponse newProduct = productCrudUseCase.create(PRODUCT_REQUEST);

        assertEquals(newProduct, PRODUCT_RESPONSE);
    }

    @Test
    public void updateProduct_ReturnsProduct() {
        when(apiProductsPortOut.update(PRODUCT_REQUEST, 2L)).thenReturn(Optional.of(PRODUCT_RESPONSE));

        Optional<ProductResponse> updatedProduct = productCrudUseCase.update(PRODUCT_REQUEST, 2L);

        assertThat(updatedProduct).isPresent();
        assertEquals(updatedProduct.get(), PRODUCT_RESPONSE);
    }

    @Test
    public void deleteProduct_ReturnsNoProduct() {
        productCrudUseCase.delete(2L);

        verify(apiProductsPortOut).delete(2L);
    }

    @Test
    public void getAllProducts_ReturnsAllProducts() {
        when(apiProductsPortOut.findAll()).thenReturn(PRODUCT_RESPONSE_LIST);

        List<ProductResponse> products = productCrudUseCase.findAll();

        assertEquals(products, PRODUCT_RESPONSE_LIST);
    }

    @Test
    public void getProductById_ReturnsProduct(){
        when(apiProductsPortOut.findById(2L)).thenReturn(Optional.of(PRODUCT_RESPONSE));

        Optional<ProductResponse> productResponse = apiProductsPortOut.findById(2L);

        assertThat(productResponse).isPresent();
        assertEquals(productResponse.get(), PRODUCT_RESPONSE);
    }

    @Test
    public void searchProductByPrice_ReturnsProducts(){
        when(apiProductsPortOut.search(100, 100)).thenReturn(PRODUCT_RESPONSE_LIST);

        List<ProductResponse> products = productCrudUseCase.search(100, 100);

        assertEquals(products, PRODUCT_RESPONSE_LIST);
    }
}
