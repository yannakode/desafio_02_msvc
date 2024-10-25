package org.msvc.product_managment.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.msvc.product_managment.model.Product;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductManagmentRequest {
    private Product product;

    private Integer quantity;
}
