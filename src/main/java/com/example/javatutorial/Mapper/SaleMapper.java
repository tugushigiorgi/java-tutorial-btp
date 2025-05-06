package com.example.javatutorial.Mapper;


import com.example.javatutorial.Dto.RegionDTO;
import com.example.javatutorial.Dto.SaleDTO;
import com.example.javatutorial.namespaces.northwind.Region;
import com.example.javatutorial.namespaces.northwind.Sales_by_Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SaleMapper {

  @Mappings({
      @Mapping(source = "categoryID", target = "categoryID"),
      @Mapping(source = "categoryName", target = "categoryName"),
      @Mapping(source = "productName", target = "productName"),
      @Mapping(source = "productSales", target = "productSales"),


  })
  SaleDTO toDto(Sales_by_Category entity);


}
