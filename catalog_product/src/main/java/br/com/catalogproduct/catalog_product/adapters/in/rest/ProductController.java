package br.com.catalogproduct.catalog_product.adapters.in.rest;

import br.com.catalogproduct.catalog_product.adapters.out.ProductServicepPortOutImpl;
import br.com.catalogproduct.catalog_product.application.service.ProductCrudUseCaseImpl;
import br.com.catalogproduct.catalog_product.domain.dto.ProductRequest;
import br.com.catalogproduct.catalog_product.domain.dto.ProductResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping
public class ProductController {

    @Autowired
    private ProductCrudUseCaseImpl productCrudUseCase;

    @PostMapping
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductRequest productRequest, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return validate(bindingResult);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(productCrudUseCase.create(productRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@Valid @RequestBody ProductRequest productRequest, @PathVariable Long id, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return validate(bindingResult);
        }
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
    public ResponseEntity<?> filterByPrice(@RequestParam(required = false) Integer min, @RequestParam(required = false) Integer max) {
        if(min == null & max == null) return ResponseEntity.status(HttpStatus.OK).body(productCrudUseCase.findAll());

        if(min == null & max != null) return ResponseEntity.status(HttpStatus.OK).body(productCrudUseCase.search(null, max));

        if(max == null) return ResponseEntity.status(HttpStatus.OK).body(productCrudUseCase.search(min, null));

        return ResponseEntity.status(HttpStatus.OK).body(productCrudUseCase.search(min, max));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productCrudUseCase.delete(id);
    }

    private ResponseEntity<Map<String, String>> validate(BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>();
        bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
