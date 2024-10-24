package br.com.catalogproduct.catalog_product.adapters.out;

import br.com.catalogproduct.catalog_product.adapters.in.rest.exceptions.ProductNotFoundException;
import br.com.catalogproduct.catalog_product.adapters.out.repositories.ProductRepository;
import br.com.catalogproduct.catalog_product.domain.dto.ProductResponse;
import br.com.catalogproduct.catalog_product.domain.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static br.com.catalogproduct.catalog_product.commons.ProductConstraits.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class ProductServicePortOutImplTest {

    @InjectMocks
    private ProductServicepPortOutImpl service;

    @Mock
    private ProductRepository repository;

    @Mock
    private ModelMapper mapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createProduct_WithValidData_ReturnsProduct(){
        when(mapper.map(PRODUCT_REQUEST, Product.class)).thenReturn(PRODUCT);
        when(repository.save(any(Product.class))).thenReturn(PRODUCT);
        when(mapper.map(PRODUCT, ProductResponse.class)).thenReturn(PRODUCT_RESPONSE);

        ProductResponse productResponse = service.create(PRODUCT_REQUEST);

        assertEquals(PRODUCT_RESPONSE, productResponse);
        verify(repository, times(1)).save(PRODUCT);
    }

    @Test
    public void updateProduct_WithValidData_ReturnsProduct(){
        when(mapper.map(PRODUCT_REQUEST, Product.class)).thenReturn(PRODUCT);
        when(repository.findById(2L)).thenReturn(Optional.of(PRODUCT));
        when(repository.save(any(Product.class))).thenReturn(PRODUCT);
        when(mapper.map(PRODUCT, ProductResponse.class)).thenReturn(PRODUCT_RESPONSE);


        Optional<ProductResponse> updatedProduct = service.update(PRODUCT_REQUEST, 2L);

        assertTrue(updatedProduct.isPresent());
        assertEquals(PRODUCT_RESPONSE, updatedProduct.get());

        verify(repository, times(1)).findById(2L);
        verify(repository, times(1)).save(any(Product.class));
    }

    @Test
    public void deleteProduct_WithUnexistingId_ThrowsException(){
        when(repository.existsById(2L)).thenReturn(false);

        assertThatThrownBy(() -> service.delete(2L)).isInstanceOf(ProductNotFoundException.class);
    }

    @Test
    public void deleteProduct_WithValidId_ReturnsNoProduct(){
        when(repository.existsById(2L)).thenReturn(true);

        service.delete(2L);

        verify(repository).deleteById(2L);
    }

    @Test
    public void deleteProduct_WithInvalidId_ThrowsException(){
        when(repository.existsById(2L)).thenReturn(false);

        assertThatThrownBy(() -> service.delete(2L)).isInstanceOf(ProductNotFoundException.class);
    }

    @Test
    public void findProduct_WithValidId_ReturnsProduct(){
        when(repository.existsById(2L)).thenReturn(true);
        when(repository.findById(2L)).thenReturn(Optional.of(PRODUCT));
        when(mapper.map(PRODUCT, ProductResponse.class)).thenReturn(PRODUCT_RESPONSE);

        Optional<ProductResponse> product = service.findById(2L);

        assertTrue(product.isPresent());
        assertEquals(PRODUCT_RESPONSE, product.get());
    }

    @Test
    public void findProduct_WithInvalidId_ReturnsProduct(){
        when(repository.existsById(2L)).thenReturn(false);

        assertThatThrownBy(() -> service.findById(2L)).isInstanceOf(ProductNotFoundException.class);
    }

    @Test
    public void getAllProducts_ReturnsAllProducts(){
        List<Product> products = List.of(PRODUCT);
        when(repository.findAll()).thenReturn(products);
        when(mapper.map(PRODUCT, ProductResponse.class)).thenReturn(PRODUCT_RESPONSE);

        List<ProductResponse> productList = service.findAll();

        assertEquals(1, productList.size());
        assertEquals(PRODUCT_RESPONSE, productList.get(0));

        verify(repository, times(1)).findAll();
    }

    @Test
    public void searchProductByMinAndMaxPrice_ReturnsProduct(){
        when(repository.findAll()).thenReturn(PRODUCTS);
        when(mapper.map(PRODUCT, ProductResponse.class)).thenReturn(PRODUCT_RESPONSE);

        List<ProductResponse> filteredProducts = service.search(300, 1000);

        assertEquals(2, filteredProducts.size());
    }

    @Test
    public void searchProductsByMinPrice_ReturnsProduct(){
        when(repository.findAll()).thenReturn(PRODUCTS);
        when(mapper.map(PRODUCT, ProductResponse.class)).thenReturn(PRODUCT_RESPONSE);

        List<ProductResponse> filteredProducts = service.search(500, null);

        assertEquals(1, filteredProducts.size());
    }

    @Test
    public void searchProductsByMaxPrice_ReturnsProduct(){
        when(repository.findAll()).thenReturn(PRODUCTS);
        when(mapper.map(PRODUCT, ProductResponse.class)).thenReturn(PRODUCT_RESPONSE);

        List<ProductResponse> filteredProducts = service.search(null, 500);

        assertEquals(3, filteredProducts.size());
    }

    @Test
    public void searchProductsByNoMinPriceOrMaxPrice_ReturnsProduct(){
        when(repository.findAll()).thenReturn(PRODUCTS);
        when(mapper.map(PRODUCT, ProductResponse.class)).thenReturn(PRODUCT_RESPONSE);

        List<ProductResponse> filteredProducts = service.search(null, null);

        assertEquals(4, filteredProducts.size());
    }
}
