package com.example.javatutorial.Controllers;

import com.example.javatutorial.Dto.ProductDTO;
import com.example.javatutorial.Service.NorthWindService;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/odata")
@RequiredArgsConstructor
@RequestMapping("/odata")
public class ODataController {
  private final NorthWindService northWindService;

  @GetMapping("/products")
  public ResponseEntity<List<ProductDTO>> getProducts() throws IOException {
    var getData = northWindService.getProductList();
    if (getData.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(getData);
  }








  @GetMapping(value = "/filter/{source}", produces = "application/json")
  public String filterData(@PathVariable String source, @PathVariable MultiValueMap<String, String> constrains) throws IOException {
    return northWindService.filterList(source, constrains);
  }

}
