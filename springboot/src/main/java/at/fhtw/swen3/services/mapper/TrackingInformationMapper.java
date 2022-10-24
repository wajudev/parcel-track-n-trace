package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.GeorCoordinateEntity;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TrackingInformationMapper {
    TrackingInformationMapper INSTANCE = Mappers.getMapper(TrackingInformationMapper.class);

    GeorCoordinateEntity dtoToEntity(GeoCoordinate geoCoordinate);

    GeoCoordinate entityToDto(GeorCoordinateEntity geoCoordinate);
}
