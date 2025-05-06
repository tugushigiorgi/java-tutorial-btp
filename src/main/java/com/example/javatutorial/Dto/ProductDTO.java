package com.example.javatutorial.Dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ProductDTO {

  private Integer productID;
  private String productName;
  private BigDecimal unitPrice;
  private Boolean discontinued;
  private Integer unitsInStock;

}