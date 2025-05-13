package com.example.javatutorial.Service.imp;
import static com.example.javatutorial.ConstControllerMessages.PRODUCT_NOT_FOUND_WITH_ID;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import com.example.javatutorial.Dto.CreateProductDto;
import com.example.javatutorial.Dto.ProductDTO;
import com.example.javatutorial.Entities.Product;
import com.example.javatutorial.Mapper.ProductMapper;
import com.example.javatutorial.Repository.ProductRepository;
import com.example.javatutorial.Service.ProductService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class ProductServiceImp implements ProductService {
  private final ProductRepository productRepository;
  private final ProductMapper productMapper;
  @Override
  @Transactional
  public void createProduct(CreateProductDto product) {
    var newProduct = Product.builder()
        .name(product.getProductName())
        .price(product.getUnitPrice()).build();
    productRepository.save(newProduct);
  }

  @Override
  @Transactional
  public ProductDTO getProductById(Long id) {
    return productRepository.findById(id)
        .map(productMapper::toDto)
        .orElse(null);
  }

  @Override
  @Transactional
  public List<ProductDTO> getAllProducts() {
    return productRepository.findAll().stream()
        .map(productMapper::toDto)
        .toList();
  }

  @Override
  @Transactional
  public void deleteById(Long id) {
    var getProduct = productRepository.findById(id)
        .orElseThrow(() ->
            new ResponseStatusException(NOT_FOUND, PRODUCT_NOT_FOUND_WITH_ID + id)
        );
    productRepository.delete(getProduct);
  }
}
