package org.msvc.product_managment.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private String name;

    private String description;

    private double price;
}
