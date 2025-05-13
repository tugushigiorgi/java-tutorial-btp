package com.example.javatutorial.Mapper;
import com.example.javatutorial.Dto.ProductDTO;
import com.example.javatutorial.namespaces.northwind.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {
  @Mappings({
      @Mapping(source = "productID", target = "productID"),
      @Mapping(source = "productName", target = "productName"),
      @Mapping(source = "unitPrice", target = "unitPrice"),
      @Mapping(source = "discontinued", target = "discontinued"),
      @Mapping(source = "unitsInStock", target = "unitsInStock"),})
  ProductDTO toDto(Product entity);
  @Mappings({
      @Mapping(source = "id", target = "productID"),
      @Mapping(source = "name", target = "productName"),
      @Mapping(source = "price", target = "unitPrice"),})
  ProductDTO toDto(com.example.javatutorial.Entities.Product product);


}

