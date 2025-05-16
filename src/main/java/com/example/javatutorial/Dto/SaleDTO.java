package com.example.javatutorial.Dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class SaleDTO {
  private Integer categoryID;
  private String categoryName;
  private String productName;
  private BigDecimal productSales;
}