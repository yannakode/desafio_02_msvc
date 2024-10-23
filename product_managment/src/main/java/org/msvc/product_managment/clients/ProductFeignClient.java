package org.msvc.product_managment.clients;

import org.msvc.product_managment.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "catalog_product", url = "http://localhost:9000/")
public interface ProductFeignClient {

    @GetMapping
    List<Product> findAll();

    @GetMapping("{id}")
    Product findById(@PathVariable Long id);
}
