package com.example.javatutorial.Service;

import com.example.javatutorial.Dto.ProductDTO;
import com.example.javatutorial.Dto.RegionDTO;
import java.io.IOException;
import java.util.List;

public interface NorthWindService {
  List<ProductDTO> getProductList();

  ProductDTO getProductById(int id);

  List<RegionDTO> getRegionList();
}
