package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.AddressEntity;
import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RecipientMapper {
    RecipientMapper INSTANCE = Mappers.getMapper(RecipientMapper.class);

    GeoCoordinateEntity dtoToEntity(GeoCoordinate geoCoordinate);

    GeoCoordinate entityToDto(GeoCoordinateEntity geoCoordinate);

    @Mapping(source = "postalCode",target ="postalcode")
    AddressEntity recipientEntityToAddressEntitiy(RecipientEntity recipient);
}
