package br.com.catalogproduct.catalog_product.application.ports.in;

import br.com.catalogproduct.catalog_product.domain.dto.ProductRequest;
import br.com.catalogproduct.catalog_product.domain.dto.ProductResponse;

import java.util.List;
import java.util.Optional;

public interface ProductCrudUseCase {

    ProductResponse create(ProductRequest productRequest);

    Optional<ProductResponse> update(ProductRequest productRequest, Long productId);

    void delete(Long id);

    Optional<ProductResponse> findById(Long id);

    List<ProductResponse> findAll();

    List<ProductResponse> search(Integer min, Integer max);
}
