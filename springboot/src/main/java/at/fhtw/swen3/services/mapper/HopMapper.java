package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.TruckEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.Warehouse;
import org.mapstruct.Condition;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.SubclassMapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HopMapper {
    HopMapper INSTANCE = Mappers.getMapper(HopMapper.class);

    @Condition
    default boolean isHop(Hop hop){
        return hop.getHopType().equals("hop");
    }
    //@SubclassMapping(source = Warehouse.class, target = WarehouseEntity.class)
    HopEntity dtoToEntity(Hop hop);

    Hop entityToDto(HopEntity geoCoordinate);


}
