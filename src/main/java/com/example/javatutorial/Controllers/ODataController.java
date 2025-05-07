package com.example.javatutorial.Controllers;
import static org.springframework.util.ObjectUtils.isEmpty;
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
public class ODataController {
  private final NorthWindService northWindService;

  @GetMapping(value = "/products", produces = "application/json")
  public ResponseEntity<List<ProductDTO>> getProducts() {
    return handleEmptyList(northWindService.getProductList());
  }
  @GetMapping(value = "/products/{id}", produces = "application/json")
  public ResponseEntity<ProductDTO> getProductById(@PathVariable int id) {
    return handleItemNotFoundOrOk(northWindService.getProductById(id));
  }
  @GetMapping(value = "/regions", produces = "application/json")
  public ResponseEntity<List<RegionDTO>> getRegions() {
    return handleEmptyList(northWindService.getRegionList());
  }
  @GetMapping(value = "/sales", produces = "application/json")
  public ResponseEntity<List<SaleDTO>> getSalesByCategory() {
    return handleEmptyList(northWindService.salesByCategory());
  }
  private <T> ResponseEntity<List<T>> handleEmptyList(List<T> data) {
    return data.isEmpty()
        ? ResponseEntity.noContent().build()
        : ResponseEntity.ok(data);
  }
  private <T> ResponseEntity<T> handleItemNotFoundOrOk(T data) {
    return isEmpty(data)
        ? ResponseEntity.noContent().build()
        : ResponseEntity.ok(data);
  }
}
