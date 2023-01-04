package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.services.dto.*;
import org.mapstruct.Condition;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {GeoCoordinateMapper.class, WarehouseNextHopsMapper.class})
public interface WarehouseMapper {
    WarehouseMapper INSTANCE = Mappers.getMapper(WarehouseMapper.class);

    WarehouseEntity dtoToEntity(Warehouse warehouse);

    Warehouse entityToDto(WarehouseEntity warehouse);

    @Condition
    default boolean isHop(Warehouse warehouse){
        return warehouse.getHopType().equals("warehouse");
    }

}
