package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.*;
import at.fhtw.swen3.services.dto.Error;
import at.fhtw.swen3.services.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GeoCoordinateMapper {
    GeoCoordinateMapper INSTANCE = Mappers.getMapper(GeoCoordinateMapper.class);

    GeorCoordinateEntity dtoToEntity(GeoCoordinate geoCoordinate);

    GeoCoordinate entityToDto(GeorCoordinateEntity geoCoordinate);
}
