package org.msvc.product_managment.model;

import lombok.Data;

@Data
public class Product {
    private Long id;

    private String name;

    private String description;

    private double price;
}
