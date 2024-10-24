package br.com.catalogproduct.catalog_product.adapters.in.rest;

import br.com.catalogproduct.catalog_product.application.service.ProductCrudUseCaseImpl;
import br.com.catalogproduct.catalog_product.domain.dto.ProductRequest;
import br.com.catalogproduct.catalog_product.domain.dto.ProductResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping
public class ProductController {

    private final ProductCrudUseCaseImpl productCrudUseCase;

    public ProductController(ProductCrudUseCaseImpl productCrudUseCase) {
        this.productCrudUseCase = productCrudUseCase;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest productRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productCrudUseCase.create(productRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@Valid @RequestBody ProductRequest productRequest, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(productCrudUseCase.update(productRequest, id).get());
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productCrudUseCase.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(productCrudUseCase.findById(id).get());
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> filterByPrice(@RequestParam(required = false) Integer min, @RequestParam(required = false) Integer max) {
        return ResponseEntity.status(HttpStatus.OK).body(productCrudUseCase.search(min, max));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productCrudUseCase.delete(id);
    }


}
