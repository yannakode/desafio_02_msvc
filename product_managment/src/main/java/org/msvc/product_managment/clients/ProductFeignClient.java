package org.msvc.product_managment.clients;

import org.msvc.product_managment.model.Product;
import org.msvc.product_managment.model.dtos.ProductRequest;
import org.msvc.product_managment.model.dtos.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "catalog-product", url = "http://localhost:9000")
public interface ProductFeignClient {

    @GetMapping
    List<Product> findAll();

    @GetMapping("/{id}")
    Product findById(@PathVariable Long id);

    @PostMapping
    ProductResponse save(@RequestBody ProductRequest productRequest);

    @PutMapping("/{id}")
    ProductResponse update(@RequestBody ProductRequest productRequest, @PathVariable Long id);

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id);

    @GetMapping("/search")
    List<Product> filterByPrice(@RequestParam(required = false) Integer min, @RequestParam(required = false) Integer max);
}
