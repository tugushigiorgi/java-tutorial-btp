package com.example.javatutorial.Controllers;

import static com.example.javatutorial.util.ControllerResponse.handleItemOrNotFound;
import static com.example.javatutorial.util.ControllerResponse.handleList;

import com.example.javatutorial.Dto.ProductDTO;
import com.example.javatutorial.Dto.RegionDTO;
import com.example.javatutorial.Dto.SaleDTO;
import com.example.javatutorial.Service.NorthWindService;
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
public class OdataController {
  private final NorthWindService northWindService;

  @GetMapping(value = "/products", produces = "application/json")
  public ResponseEntity<List<ProductDTO>> getProducts() {
    return handleList(northWindService.getProductList());
  }

  @GetMapping(value = "/products/{id}", produces = "application/json")
  public ResponseEntity<ProductDTO> getProductById(@PathVariable int id) {
    return handleItemOrNotFound(northWindService.getProductById(id));
  }

  @GetMapping(value = "/regions", produces = "application/json")
  public ResponseEntity<List<RegionDTO>> getRegions() {
    return handleList(northWindService.getRegionList());
  }

  @GetMapping(value = "/sales", produces = "application/json")
  public ResponseEntity<List<SaleDTO>> getSalesByCategory() {
    return handleList(northWindService.salesByCategory());
  }
}
