package com.example.javatutorial.Controllers;
import static com.example.javatutorial.ConstControllerMessages.PRODUCT_CREATED;
import static com.example.javatutorial.util.ControllerResponse.handleItemOrNotFound;
import static com.example.javatutorial.util.ControllerResponse.handleList;
import com.example.javatutorial.Dto.CreateProductDto;
import com.example.javatutorial.Dto.ProductDTO;
import com.example.javatutorial.Service.ProductService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hana")
@AllArgsConstructor
@Slf4j
public class ProductController {
  private final ProductService productService;

  @PostMapping
  public ResponseEntity createProduct(@Valid @RequestBody CreateProductDto product) {
    log.info("Received request to create product: {}", product);
    productService.createProduct(product);
    return ResponseEntity.ok(PRODUCT_CREATED);
  }

  @GetMapping(value = "/{id}", produces = "application/json")
  public ResponseEntity<ProductDTO> getProduct(@PathVariable Long id) {
    log.info("Fetching product with ID: {}", id);
    return handleItemOrNotFound(productService.getProductById(id));
  }

  @GetMapping(produces = "application/json")
  public ResponseEntity<List<ProductDTO>> getAllProducts() {
    log.info("Fetching all products.");
    return handleList(productService.getAllProducts());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity deleteProduct(@PathVariable Long id) {
    log.info("Received request to delete product with ID: {}", id);
    productService.deleteById(id);
    log.info("Product with ID {} deleted successfully.", id);
    return ResponseEntity.noContent().build();
  }
}
