package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.*;
import at.fhtw.swen3.services.dto.*;
import at.fhtw.swen3.services.dto.Error;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ParcelMapper {
    ParcelMapper INSTANCE = Mappers.getMapper(ParcelMapper.class);

    @Mapping(source = "parcelDTO.weight",target ="weight")
    @Mapping(source = "parcelDTO.recipient",target ="sender")
    @Mapping(source = "parcelDTO.sender",target ="recipient")
    @Mapping(source = "newParcelInfoDTO.trackingId",target ="trackingId")
    @Mapping(source = "trackingInformationDTO.state",target ="state")
    @Mapping(source = "trackingInformationDTO.futureHops",target ="futureHops")
    @Mapping(source = "trackingInformationDTO.visitedHops",target ="visitedHops")
    ParcelEntity from(NewParcelInfo newParcelInfoDTO, Parcel parcelDTO, TrackingInformation trackingInformationDTO);

    @Mapping(source = "recipient.name", target = "name")
    @Mapping(source = "recipient.street", target = "street")
    @Mapping(source = "recipient.postalCode", target = "postalCode")
    @Mapping(source = "recipient.city", target = "city")
    @Mapping(source = "recipient.country", target = "country")
    RecipientEntity map(Recipient recipient);

    @Mapping(source = "hopArrival.code",target = "code")
    @Mapping(source = "hopArrival.description", target = "description")
    @Mapping(source = "hopArrival.dateTime", target = "dateTime")
    HopArrivalEntity map(HopArrival hopArrival);

    @Mapping(source = "error.errorMessage", target = "errorMessage")
    ErrorEntity map(Error error);

    @Mapping(source = "geoCoordinate.lat",target = "lat")
    @Mapping(source = "geoCoordinate.lon",target = "lon")
    GeorCoordinateEntity map(GeoCoordinate geoCoordinate);
}
