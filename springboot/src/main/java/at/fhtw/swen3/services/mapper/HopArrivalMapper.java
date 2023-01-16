package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.services.decorator.HopMapperDecorator;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.HopArrival;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {GeoCoordinateMapper.class})
public interface HopArrivalMapper {
    HopArrivalMapper INSTANCE = Mappers.getMapper(HopArrivalMapper.class);

    HopArrivalEntity dtoToEntity(HopArrival hopArrival);

    HopArrival entityToDto(HopArrivalEntity hopArrival);

    List<HopArrival> entitiesToDtos(List<HopArrivalEntity> hopArrivals);
}
