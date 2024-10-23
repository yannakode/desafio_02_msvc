package br.com.catalogproduct.catalog_product.application.ports.out;

import br.com.catalogproduct.catalog_product.domain.dto.ProductRequest;
import br.com.catalogproduct.catalog_product.domain.dto.ProductResponse;

import java.util.List;
import java.util.Optional;

public interface ApiProductsPortOut {

    ProductResponse create(ProductRequest productRequest);

    Optional<ProductResponse> update(ProductRequest productRequest, Long productId);

    void delete(Long productId);

    Optional<ProductResponse> findById(Long productId);

    List<ProductResponse> findAll();

    List<ProductResponse> search(Integer min, Integer max);
}
