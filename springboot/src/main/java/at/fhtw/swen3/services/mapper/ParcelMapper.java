package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.*;
import at.fhtw.swen3.services.dto.*;
import at.fhtw.swen3.services.dto.Error;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Mapper
@Service
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

    ParcelEntity map(Parcel parcel);

    ParcelEntity map(String trackingId, Parcel parcel);

    RecipientEntity map(Recipient recipient);

    HopArrivalEntity map(HopArrival hopArrival);

    ErrorEntity map(Error error);
    GeoCoordinateEntity map(GeoCoordinate geoCoordinate);
}
