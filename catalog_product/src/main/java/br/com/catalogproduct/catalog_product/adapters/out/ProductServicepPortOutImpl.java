package br.com.catalogproduct.catalog_product.adapters.out;

import br.com.catalogproduct.catalog_product.adapters.in.rest.exceptions.ProductNotFoundException;
import br.com.catalogproduct.catalog_product.adapters.out.repositories.ProductRepository;
import br.com.catalogproduct.catalog_product.application.ports.out.ApiProductsPortOut;
import br.com.catalogproduct.catalog_product.domain.dto.ProductRequest;
import br.com.catalogproduct.catalog_product.domain.dto.ProductResponse;
import br.com.catalogproduct.catalog_product.domain.model.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServicepPortOutImpl implements ApiProductsPortOut {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductResponse create(ProductRequest productRequest) {
        Product product = modelMapper.map(productRequest, Product.class);
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductResponse.class);
    }

    @Override
    public Optional<ProductResponse> update(ProductRequest productRequest, Long productId) {
        if(!productRepository.existsById(productId)){
            throw new ProductNotFoundException(productId);
        }

        Product product = productRepository.findById(productId).get();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        Product savedProduct = productRepository.save(product);
        ProductResponse productResponse = modelMapper.map(product, ProductResponse.class);

        return Optional.of(productResponse);
    }

    @Override
    public void delete(Long productId) {
        if(!productRepository.existsById(productId)){
            throw new ProductNotFoundException(productId);
        }

        productRepository.deleteById(productId);
    }

    @Override
    public Optional<ProductResponse> findById(Long productId) {
        if(!productRepository.existsById(productId)){
            throw new ProductNotFoundException(productId);
        }

        Product product = productRepository.findById(productId).get();

        return Optional.of(modelMapper.map(product, ProductResponse.class));
    }

    @Override
    public List<ProductResponse> findAll() {
        List<Product> products = productRepository.findAll();

        List<ProductResponse> productResponses = products
                .stream()
                .map(p -> modelMapper.map(p, ProductResponse.class))
                .toList();
        return productResponses;
    }

    @Override
    public List<ProductResponse> search(Integer min, Integer max){
        List<Product> productResponses = productRepository.findAll()
                .stream()
                .filter(p -> min == null || p.getPrice() >= min)
                .filter(p -> max == null || p.getPrice() <= max)
                .toList();
        return productResponses.stream().map(p-> modelMapper.map(p, ProductResponse.class)).toList();
    }
}
