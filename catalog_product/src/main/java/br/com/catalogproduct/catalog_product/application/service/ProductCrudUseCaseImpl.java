package br.com.catalogproduct.catalog_product.application.service;

import br.com.catalogproduct.catalog_product.application.ports.in.ProductCrudUseCase;
import br.com.catalogproduct.catalog_product.application.ports.out.ApiProductsPortOut;
import br.com.catalogproduct.catalog_product.domain.dto.ProductRequest;
import br.com.catalogproduct.catalog_product.domain.dto.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCrudUseCaseImpl implements ProductCrudUseCase {

    @Autowired
    private ApiProductsPortOut apiProductsPortOut;

    @Override
    public ProductResponse create(ProductRequest productRequest) {
        return apiProductsPortOut.create(productRequest);
    }

    @Override
    public Optional<ProductResponse> update(ProductRequest productRequest, Long productId) {
        return apiProductsPortOut.update(productRequest, productId);
    }

    @Override
    public void delete(Long id) {
        apiProductsPortOut.delete(id);
    }

    @Override
    public Optional<ProductResponse> findById(Long id) {
        return apiProductsPortOut.findById(id);
    }

    @Override
    public List<ProductResponse> findAll() {
        return apiProductsPortOut.findAll();
    }

    @Override
    public List<ProductResponse> search(Integer min, Integer max) {
        return apiProductsPortOut.search(min, max);
    }
}
