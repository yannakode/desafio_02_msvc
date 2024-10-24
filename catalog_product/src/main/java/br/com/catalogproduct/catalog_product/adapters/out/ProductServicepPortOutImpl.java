package br.com.catalogproduct.catalog_product.adapters.out;

import br.com.catalogproduct.catalog_product.adapters.in.rest.exceptions.ProductNotFoundException;
import br.com.catalogproduct.catalog_product.adapters.out.repositories.ProductRepository;
import br.com.catalogproduct.catalog_product.application.ports.out.ApiProductsPortOut;
import br.com.catalogproduct.catalog_product.domain.dto.ProductRequest;
import br.com.catalogproduct.catalog_product.domain.dto.ProductResponse;
import br.com.catalogproduct.catalog_product.domain.model.Product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServicepPortOutImpl implements ApiProductsPortOut {

    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;

    public ProductServicepPortOutImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductResponse create(ProductRequest productRequest) {
        Product product = modelMapper.map(productRequest, Product.class);
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductResponse.class);
    }

    @Override
    public Optional<ProductResponse> update(ProductRequest productRequest, Long productId) {
        Optional<Product> productOp = Optional.ofNullable(productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId)));

        Product product = productOp.get();
        product.setName(productRequest.name());
        product.setDescription(productRequest.description());
        product.setPrice(productRequest.price());
        Product savedProduct = productRepository.save(product);

        return Optional.of(modelMapper.map(savedProduct, ProductResponse.class));
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
        Product productOp = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        return Optional.of(modelMapper.map(productOp, ProductResponse.class));
    }

    @Override
    public List<ProductResponse> findAll() {
        List<Product> products = productRepository.findAll();

        return products
                .stream()
                .map(p -> modelMapper.map(p, ProductResponse.class))
                .toList();
    }

    @Override
    public List<ProductResponse> search(Integer min, Integer max){
        List<Product> productResponses = productRepository.findAll();

        if(min == null && max == null) return productResponses.stream()
                .map(p-> modelMapper.map(p, ProductResponse.class))
                .toList();;

        if(min == null) return productResponses.stream()
                .filter(p -> p.getPrice() <= max)
                .map(p-> modelMapper.map(p, ProductResponse.class))
                .toList();

        if(max == null) return productResponses.stream()
                .filter(p -> p.getPrice() >= min)
                .map(p-> modelMapper.map(p, ProductResponse.class))
                .toList();

        return productResponses.stream()
                .filter(p -> p.getPrice() >= min && p.getPrice() <= max)
                .map(p-> modelMapper.map(p, ProductResponse.class)).toList();
    }
}
