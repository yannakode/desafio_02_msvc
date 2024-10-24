//package br.com.catalogproduct.catalog_product.adapters.in;
//
//import br.com.catalogproduct.catalog_product.adapters.in.rest.ProductController;
//import br.com.catalogproduct.catalog_product.adapters.in.rest.exceptions.ProductNotFoundException;
//import br.com.catalogproduct.catalog_product.application.service.ProductCrudUseCaseImpl;
//import br.com.catalogproduct.catalog_product.domain.dto.ProductResponse;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
//import static br.com.catalogproduct.catalog_product.commons.ProductConstraits.*;
//import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class ProductControllerTest {
//    @InjectMocks
//    private ProductController productController;
//
//    @Mock
//    ProductCrudUseCaseImpl productCrudUseCase;
//
//    @Test
//    public void createProduct_WithValidData_ReturnProduct() {
//        when(productCrudUseCase.create(PRODUCT_REQUEST)).thenReturn(PRODUCT_RESPONSE);
//
//        ResponseEntity<?> response = productController.createProduct(PRODUCT_REQUEST);
//
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//        assertEquals(PRODUCT_RESPONSE, response.getBody());
//    }
//
//    @Test
//    public void createProduct_WithNullData_ThrowException() {
//        ResponseEntity<ProductResponse> response = productController.createProduct(PRODUCT_REQUEST_NULL);
//        ProductResponse productResponse = response.getBody();
//        Map<String, String> errors = productResponse.get
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        assertEquals("Name field must not be empty", errors.get("name"));
//        assertEquals("Description field must not be empty", errors.get("description"));
//        assertEquals("Price field must be at least 1", errors.get("price"));
//    }
//
//    @Test
//    public void createProduct_WithEmptyName_ThrowException() {
//        when(bindingResult.hasErrors()).thenReturn(true);
//        when(bindingResult.getFieldErrors()).thenReturn(List.of(
//                new FieldError("productRequest", "name", "Name field must not be empty")
//        ));
//
//        ResponseEntity<?> response = productController.createProduct(PRODUCT_REQUEST_INVALID_NAME_REQUEST, bindingResult);
//        Map<String, String> errors = (Map<String, String>) response.getBody();
//
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        assertEquals("Name field must not be empty", errors.get("name"));
//    }
//
//    @Test
//    public void createProduct_WithEmptyDescription_ThrowException() {
//        when(bindingResult.hasErrors()).thenReturn(true);
//        when(bindingResult.getFieldErrors()).thenReturn(List.of(
//                new FieldError("productRequest", "description", "Description field must not be empty")
//        ));
//
//        ResponseEntity<?> response = productController.createProduct(PRODUCT_REQUEST_INVALID_DESCRIPTION_REQUEST, bindingResult);
//        Map<String, String> errors = (Map<String, String>) response.getBody();
//
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        assertEquals("Description field must not be empty", errors.get("description"));
//    }
//
//    @Test
//    public void createProduct_WithEmptyPrice_ThrowException() {
//        when(bindingResult.hasErrors()).thenReturn(true);
//        when(bindingResult.getFieldErrors()).thenReturn(List.of(
//                new FieldError("productRequest", "price", "Price field must be at least 1")
//        ));
//
//        ResponseEntity<?> response = productController.createProduct(PRODUCT_REQUEST_NULL_PRICE_REQUEST, bindingResult);
//        Map<String, String> errors = (Map<String, String>) response.getBody();
//
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        assertEquals("Price field must be at least 1", errors.get("price"));
//    }
//
//    @Test
//    public void updateProduct_WithValidData_ReturnProduct() {
//        when(bindingResult.hasErrors()).thenReturn(false);
//        when(productCrudUseCase.update(PRODUCT_REQUEST, 2L)).thenReturn(Optional.of(PRODUCT_RESPONSE));
//
//        ResponseEntity<?> response = productController.updateProduct(PRODUCT_REQUEST, 2L);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(PRODUCT_RESPONSE, response.getBody());
//    }
//
//    @Test
//    public void updateProduct_WithNullData_ThrowException() {
//        when(bindingResult.hasErrors()).thenReturn(true);
////        when(bindingResult.getFieldErrors()).thenReturn(List.of(
////                new FieldError("productRequest", "name", "Name field must not be empty"),
////                new FieldError("productResponse", "description", "Description field must not be empty"),
////                new FieldError("productResponse", "price", "Price field must be at least 1")
////        ));
//
//        ResponseEntity<?> response = productController.updateProduct(PRODUCT_REQUEST_NULL, 2L);
//        Map<String, String> errors = (Map<String, String>) response.getBody();
//
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        assertEquals("Name field must not be empty", errors.get("name"));
//        assertEquals("Description field must not be empty", errors.get("description"));
//        assertEquals("Price field must be at least 1", errors.get("price"));
//    }
//
//    @Test
//    public void updateProduct_WithEmptyName_ThrowException() {
//        when(bindingResult.hasErrors()).thenReturn(true);
//        when(bindingResult.getFieldErrors()).thenReturn(List.of(
//                new FieldError("productRequest", "name", "Name field must not be empty")
//        ));
//
//        ResponseEntity<?> response = productController.updateProduct(PRODUCT_REQUEST_INVALID_NAME_REQUEST, 2L, bindingResult);
//        Map<String, String> errors = (Map<String, String>) response.getBody();
//
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        assertEquals("Name field must not be empty", errors.get("name"));
//    }
//
//    @Test
//    public void updateProduct_WithEmptyDescription_ThrowException() {
//        when(bindingResult.hasErrors()).thenReturn(true);
//        when(bindingResult.getFieldErrors()).thenReturn(List.of(
//                new FieldError("productRequest", "description", "Description field must not be empty")
//        ));
//
//        ResponseEntity<?> response = productController.updateProduct(PRODUCT_REQUEST_INVALID_DESCRIPTION_REQUEST, 2L, bindingResult);
//        Map<String, String> errors = (Map<String, String>) response.getBody();
//
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        assertEquals("Description field must not be empty", errors.get("description"));
//    }
//
//    @Test
//    public void updateProduct_WithEmptyPrice_ThrowException() {
//        when(bindingResult.hasErrors()).thenReturn(true);
//        when(bindingResult.getFieldErrors()).thenReturn(List.of(
//                new FieldError("productRequest", "price", "Price field must be at least 1")
//        ));
//
//        ResponseEntity<?> response = productController.updateProduct(PRODUCT_REQUEST_NULL_PRICE_REQUEST, 2L, bindingResult);
//        Map<String, String> errors = (Map<String, String>) response.getBody();
//
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        assertEquals("Price field must be at least 1", errors.get("price"));
//    }
//
//    @Test
//    public void updateProduct_WithInvalidId_ThrowException() {
//        when(bindingResult.hasErrors()).thenReturn(false);
//        when(productCrudUseCase.update(PRODUCT_REQUEST, 1L)).thenThrow(new ProductNotFoundException(1L));
//
//        assertThatThrownBy(()-> productController.updateProduct(PRODUCT_REQUEST, 1L, bindingResult))
//                .isInstanceOf(ProductNotFoundException.class)
//                .hasMessage("Product not found with id 1");
//    }
//
//    @Test
//    public void getAllProducts_ReturnsAllProducts() {
//        List<ProductResponse> productResponseList = new ArrayList<>();
//        productResponseList.add(PRODUCT_RESPONSE);
//        when(productCrudUseCase.findAll()).thenReturn(productResponseList);
//
//        ResponseEntity<List<ProductResponse>> response = productController.getAllProducts();
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(productResponseList, response.getBody());
//        assertTrue(!response.getBody().isEmpty());
//    }
//
//    @Test
//    public void getProduct_WithValidId_ReturnsProduct() {
//        when(productCrudUseCase.findById(2L)).thenReturn(Optional.of(PRODUCT_RESPONSE));
//
//        ResponseEntity<?> response = productController.getProductById(2L);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(PRODUCT_RESPONSE, response.getBody());
//    }
//
//    @Test
//    public void getProduct_WithInvalidId_ReturnsProduct() {
//        when(productCrudUseCase.findById(1L)).thenThrow(new ProductNotFoundException(1L));
//
//        assertThatThrownBy(()-> productController.getProductById(1L))
//                .isInstanceOf(ProductNotFoundException.class)
//                .hasMessage("Product not found with id 1");
//    }
//
//    @Test
//    public void deleteProduct_WithValidId_ReturnsNoProduct() {
//        Long productId = 1L;
//
//        productController.deleteProduct(productId);
//
//        verify(productCrudUseCase).delete(productId);
//    }
//
//    @Test
//    public void deleteProduct_WithInvalidId_ReturnsProduct() {
//        doThrow(new ProductNotFoundException(1L)).when(productCrudUseCase).delete(1L);
//
//        assertThatThrownBy(()-> productController.deleteProduct(1L))
//                .isInstanceOf(ProductNotFoundException.class)
//                .hasMessage("Product not found with id 1");
//    }
//
//    @Test
//    public void filterProduct_ByMinAndMaxPrice_ReturnsProducts() {
//        when(productCrudUseCase.search(100, 500)).thenReturn(PRODUCT_RESPONSE_LIST);
//
//        ResponseEntity<?> response = productController.filterByPrice(100, 500);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//    }
//
//    @Test
//    public void filterProduct_ByMinPrice_ReturnsProducts() {
//        when(productCrudUseCase.search(100, null)).thenReturn(PRODUCT_RESPONSE_LIST);
//
//        ResponseEntity<?> response = productController.filterByPrice(100, null);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//    }
//
//    @Test
//    public void filterProduct_ByMaxPrice_ReturnsProducts() {
//        when(productCrudUseCase.search(null, 500)).thenReturn(PRODUCT_RESPONSE_LIST);
//
//        ResponseEntity<?> response = productController.filterByPrice(null, 500);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//    }
//
//    @Test
//    public void filterProduct_ByNoMinAndNoMaxPrice_ReturnsProducts() {
//        when(productCrudUseCase.findAll()).thenReturn(PRODUCT_RESPONSE_LIST);
//
//        ResponseEntity<?> response = productController.filterByPrice(null, null);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//    }
//}
