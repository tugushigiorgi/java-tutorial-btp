package com.example.javatutorial.Service.imp;

import static com.example.javatutorial.ConstData.ODATA_BASE_URL;
import static com.example.javatutorial.ConstData.ODATA_DEST_NAME;
import static java.util.Collections.emptyList;

import com.example.javatutorial.Dto.ProductDTO;
import com.example.javatutorial.Dto.RegionDTO;
import com.example.javatutorial.Mapper.ProductMapper;
import com.example.javatutorial.Mapper.RegionMapper;
import com.example.javatutorial.Service.NorthWindService;
import com.example.javatutorial.services.DefaultNorthwindService;
import com.sap.cloud.sdk.cloudplatform.connectivity.Destination;
import com.sap.cloud.sdk.cloudplatform.connectivity.DestinationAccessor;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NorthWindServiceImp implements NorthWindService {

  private final ProductMapper productMapper;
  private final RegionMapper regionMapper;

  private DefaultNorthwindService getDestinationService() {
    return new DefaultNorthwindService()
        .withServicePath(ODATA_BASE_URL);
  }

  private Destination getDestination() {
    return DestinationAccessor.getLoader()
        .tryGetDestination(ODATA_DEST_NAME).get();
  }

  @Override
  public List<ProductDTO> getProductList() {
    var data = getDestinationService()
        .getAllProduct()
        .execute(getDestination());
    if (data.isEmpty()) {
      return emptyList();
    }
    return data.stream()
        .map(productMapper::toDto)
        .toList();
  }

  @Override
  public ProductDTO getProductById(int id) {
    var product = getDestinationService()
        .getProductByKey(id)
        .execute(getDestination());
    if (product == null) {
      return null;
    }
    return productMapper.toDto(product);
  }

  @Override
  public List<RegionDTO> getRegionList() {
    var data = getDestinationService()
        .getAllRegion()
        .execute(getDestination());

    if (data.isEmpty()) {
      return emptyList();
    }
    return data.stream()
        .map(regionMapper::toDto)
        .toList();
  }









}
