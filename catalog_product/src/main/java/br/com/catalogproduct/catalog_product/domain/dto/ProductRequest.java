package br.com.catalogproduct.catalog_product.domain.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public record ProductRequest (@NotBlank(message = "Name field must not be empty") String name,
                              @NotBlank(message = "Description field must not be empty") String description,
                              @Min(value = 1, message = "Price field must be at least 1") double price){
}
