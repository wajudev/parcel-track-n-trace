package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.ParcelEntity;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.TrackingInformation;
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
}
