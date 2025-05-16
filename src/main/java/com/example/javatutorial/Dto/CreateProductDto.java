package com.example.javatutorial.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.checkerframework.checker.index.qual.Positive;

@Data
public class CreateProductDto {
  @NotBlank(message = "Product name field is required")
  public String productName;
  @NotNull(message = "Price field is required")
  @Positive
  public Double unitPrice;
}
