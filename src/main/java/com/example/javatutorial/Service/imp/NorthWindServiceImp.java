package com.example.javatutorial.Service.imp;

import static com.example.javatutorial.ConstData.ODATA_BASE_URL;
import static com.example.javatutorial.ConstData.ODATA_DEST_NAME;

import com.example.javatutorial.Dto.ProductDTO;
import com.example.javatutorial.Mapper.ProductMapper;
import com.example.javatutorial.Service.NorthWindService;
import com.example.javatutorial.services.DefaultNorthwindService;
import com.sap.cloud.sdk.cloudplatform.connectivity.Destination;
import com.sap.cloud.sdk.cloudplatform.connectivity.DestinationAccessor;
import com.sap.cloud.sdk.cloudplatform.connectivity.HttpClientAccessor;
import io.micrometer.core.instrument.util.IOUtils;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NorthWindServiceImp implements NorthWindService {

  private final ProductMapper productMapper;


  private HttpClient getClient() {
    var destination = DestinationAccessor.getLoader().tryGetDestination(ODATA_DEST_NAME).get().asHttp();
    return HttpClientAccessor.getHttpClient(destination);
  }

  private DefaultNorthwindService getDestinationService() {
    return new DefaultNorthwindService()
        .withServicePath(ODATA_BASE_URL);
  }

  private Destination getDestination() {
    return DestinationAccessor.getLoader().tryGetDestination(ODATA_DEST_NAME).get();
  }


  @Override
  public List<ProductDTO> getProductList() throws IOException {

    var data = getDestinationService()
        .getAllProduct()
        .execute(getDestination());

    if (data.isEmpty()) {
      return Collections.emptyList();
    }
    return data.stream()
        .map(productMapper::toDto)
        .toList();
  }

  @Override
  public ProductDTO getProductById(int id) throws IOException {
    var product = getDestinationService()
        .getProductByKey(id)
        .execute(getDestination());
    if (product == null) {
      return null;
    }
    return productMapper.toDto(product);
  }


}
