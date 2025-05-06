package com.example.javatutorial.Service;

import com.example.javatutorial.Dto.ProductDTO;
import java.io.IOException;
import java.util.List;

public interface NorthWindService {
  List<ProductDTO> getProductList() throws IOException;
  ProductDTO getProductById(int id) throws IOException;
}
