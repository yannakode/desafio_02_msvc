package br.com.catalogproduct.catalog_product.adapters.out.repositories;

import br.com.catalogproduct.catalog_product.application.ports.out.ApiProductsPortOut;
import br.com.catalogproduct.catalog_product.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
