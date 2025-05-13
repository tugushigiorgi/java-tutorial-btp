package com.example.javatutorial.Service;

import com.example.javatutorial.Dto.NewProductDto;
import com.example.javatutorial.Dto.ProductDTO;
import java.util.List;

public interface ProductService {
  void createProduct(NewProductDto product);
  ProductDTO getProductById(Long id);
  List<ProductDTO> getAllProducts();
  void deleteById(Long id);
}
