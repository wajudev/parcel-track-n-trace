package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.*;
import at.fhtw.swen3.services.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WarehouseMapper {
    WarehouseMapper INSTANCE = Mappers.getMapper(WarehouseMapper.class);

    WarehouseEntity dtoToEntity(Warehouse warehouse);

    Warehouse entityToDto(WarehouseEntity warehouse);

}
