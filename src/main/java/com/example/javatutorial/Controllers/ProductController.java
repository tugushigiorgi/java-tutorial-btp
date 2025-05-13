package com.example.javatutorial.Controllers;

import static com.example.javatutorial.ConstControllerMessages.PRODUCT_CREATED;
import static com.example.javatutorial.ControllerResponse.handleItemNotFoundOrOk;
import static com.example.javatutorial.ControllerResponse.handleList;
import com.example.javatutorial.Dto.CreateProductDto;
import com.example.javatutorial.Dto.ProductDTO;
import com.example.javatutorial.Service.ProductService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
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
public class ProductController {
  private final ProductService productService;

  @PostMapping
  public ResponseEntity createProduct(@Valid @RequestBody CreateProductDto product) {
    productService.createProduct(product);
    return ResponseEntity.ok(PRODUCT_CREATED);
  }

  @GetMapping(value = "/{id}", produces = "application/json")
  public ResponseEntity<ProductDTO> getProduct(@PathVariable Long id) {
    return handleItemNotFoundOrOk(productService.getProductById(id));
  }

  @GetMapping(produces = "application/json")
  public ResponseEntity<List<ProductDTO>> getAllProducts() {
    return handleList(productService.getAllProducts());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity deleteProduct(@PathVariable Long id) {
    productService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
