package com.example.javatutorial.Mapper;

import com.example.javatutorial.Dto.RegionDTO;
import com.example.javatutorial.namespaces.northwind.Region;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RegionMapper {

  @Mappings({
      @Mapping(source = "regionID", target = "regionID"),
      @Mapping(source = "regionDescription", target = "regionDescription"),
  })
  RegionDTO toDto(Region entity);
}
