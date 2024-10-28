package org.msvc.product_managment.service.assembler;

import org.mapstruct.factory.Mappers;
import org.msvc.product_managment.model.Product;
import org.msvc.product_managment.model.ProductManagment;
import org.msvc.product_managment.model.dtos.ProductManagmentRequest;
import org.msvc.product_managment.model.dtos.ProductManagmentResponse;
import org.msvc.product_managment.model.dtos.ProductRequest;
import org.msvc.product_managment.model.dtos.ProductResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapStruct {
    MapStruct INSTANCE = Mappers.getMapper(MapStruct.class);

    Product toProduct(ProductResponse toProductResponse);
    ProductResponse toProductResponse(Product product);
    ProductRequest toProductRequest(ProductResponse productResponse);
    ProductRequest toProductRequest(Product product);

    ProductManagment toProductManagment(ProductManagmentResponse productManagmentResponse);
    ProductManagmentResponse toProductManagmentResponse(ProductManagment productManagment);
    ProductManagmentResponse toProductManagmentResponse(ProductManagmentRequest productManagmentRequest);
}
