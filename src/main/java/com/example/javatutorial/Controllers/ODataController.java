package com.example.javatutorial.Controllers;

import com.example.javatutorial.Dto.ProductDTO;
import com.example.javatutorial.Dto.RegionDTO;
import com.example.javatutorial.Service.NorthWindService;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/odata")
public class ODataController {
  private final NorthWindService northWindService;

  @GetMapping(value = "/products", produces = "application/json")
  public ResponseEntity<List<ProductDTO>> getProducts() {
    var getData = northWindService.getProductList();
    if (getData.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(getData);
  }

  @GetMapping(value = "/products/{id}", produces = "application/json")
  public ResponseEntity<ProductDTO> getProductById(@PathVariable int id) {
    var getProduct = northWindService.getProductById(id);
    if (getProduct == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(getProduct);
  }

  @GetMapping(value = "/regions", produces = "application/json")
  public ResponseEntity<List<RegionDTO>> getRegions() {
    var getRegions = northWindService.getRegionList();
    if (getRegions.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(getRegions);

  }

}
