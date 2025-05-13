package com.example.javatutorial.Service;

import com.example.javatutorial.Dto.CreateProductDto;
import com.example.javatutorial.Dto.ProductDTO;
import java.util.List;

public interface ProductService {
  void createProduct(CreateProductDto product);
  ProductDTO getProductById(Long id);
  List<ProductDTO> getAllProducts();
  void deleteById(Long id);
}
