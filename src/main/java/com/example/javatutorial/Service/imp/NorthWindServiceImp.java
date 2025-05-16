package com.example.javatutorial.Service.imp;

import static com.example.javatutorial.ConstData.ODATA_BASE_URL;
import static com.example.javatutorial.ConstData.ODATA_DEST_NAME;
import static java.util.Collections.emptyList;

import com.example.javatutorial.Dto.ProductDTO;
import com.example.javatutorial.Dto.RegionDTO;
import com.example.javatutorial.Dto.SaleDTO;
import com.example.javatutorial.Mapper.ProductMapper;
import com.example.javatutorial.Mapper.RegionMapper;
import com.example.javatutorial.Mapper.SaleMapper;
import com.example.javatutorial.Service.NorthWindService;
import com.example.javatutorial.services.DefaultNorthwindService;
import com.sap.cloud.sdk.cloudplatform.connectivity.Destination;
import com.sap.cloud.sdk.cloudplatform.connectivity.DestinationAccessor;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NorthWindServiceImp implements NorthWindService {
  private final ProductMapper productMapper;
  private final RegionMapper regionMapper;
  private final SaleMapper saleMapper;

  @Override
  public List<ProductDTO> getProductList() {
    return Optional.of(
            getDestinationService()
                .getAllProduct()
                .execute(getDestination())
        )
        .orElse(emptyList())
        .stream()
        .map(productMapper::toDto)
        .toList();
  }

  @Override
  public ProductDTO getProductById(int id) {
    return Optional.of(
            getDestinationService()
                .getProductByKey(id)
                .execute(getDestination())
        )
        .map(productMapper::toDto)
        .orElse(null);
  }

  @Override
  public List<RegionDTO> getRegionList() {
    return Optional.of(
            getDestinationService()
                .getAllRegion()
                .execute(getDestination())
        ).orElse(emptyList()).stream()
        .map(regionMapper::toDto)
        .toList();
  }

  @Override
  public List<SaleDTO> salesByCategory() {
    return Optional.of(
            getDestinationService()
                .getAllSales_by_Category()
                .execute(getDestination())
        ).orElse(emptyList())
        .stream()
        .map(saleMapper::toDto)
        .toList();
  }

  private DefaultNorthwindService getDestinationService() {
    return new DefaultNorthwindService()
        .withServicePath(ODATA_BASE_URL);
  }

  private Destination getDestination() {
    return DestinationAccessor.getLoader()
        .tryGetDestination(ODATA_DEST_NAME)
        .get();
  }
}
