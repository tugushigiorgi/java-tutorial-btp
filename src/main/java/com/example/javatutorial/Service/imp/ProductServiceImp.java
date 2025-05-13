package com.example.javatutorial.Service.imp;
import static com.example.javatutorial.ConstControllerMessages.PRODUCT_NOT_FOUND_WITH_ID;
import static java.util.Collections.emptyList;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import com.example.javatutorial.Dto.NewProductDto;
import com.example.javatutorial.Dto.ProductDTO;
import com.example.javatutorial.Entities.Product;
import com.example.javatutorial.Mapper.ProductMapper;
import com.example.javatutorial.Repository.ProductRepository;
import com.example.javatutorial.Service.ProductService;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImp implements ProductService {
  private final ProductRepository productRepository;
  private final ProductMapper productMapper;

  @Override
  public void createProduct(NewProductDto product) {
    var newProduct = Product.builder()
        .name(product.getProductName())
        .price(product.getUnitPrice()).build();
    productRepository.save(newProduct);
  }

  @Override
  public ProductDTO getProductById(Long id) {
    return productRepository.findById(id)
        .map(productMapper::toDto)
        .orElse(null);
  }

  @Override
  public List<ProductDTO> getAllProducts() {
    return Optional.of(
            productRepository.findAll()
        )
        .orElse(emptyList())
        .stream()
        .map(productMapper::toDto)
        .toList();
  }

  @Override
  public void deleteById(Long id) {
    var getProduct = productRepository.findById(id)
        .orElseThrow(() ->
            new ResponseStatusException(NOT_FOUND, PRODUCT_NOT_FOUND_WITH_ID + id)
        );
    productRepository.delete(getProduct);
  }
}
