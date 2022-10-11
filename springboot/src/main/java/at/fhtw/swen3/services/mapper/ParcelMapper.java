package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.ParcelEntity;
import at.fhtw.swen3.services.dto.NewParcelInfoDTO;
import at.fhtw.swen3.services.dto.ParcelDTO;
import at.fhtw.swen3.services.dto.TrackingInformationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ParcelMapper {
    ParcelMapper INSTANCE = Mappers.getMapper(ParcelMapper.class);

    @Mapping(source = "parcelDTO.weight",target ="weight")
    @Mapping(source = "parcelDTO.recipient",target ="sender")
    @Mapping(source = "parcelDTO.recipient",target ="recipient")
    @Mapping(source = "NewParcelInfoDTO.trackingId",target ="trackingId")
    @Mapping(source = "trackingInformationDTO.state",target ="state")
    @Mapping(source = "trackingInformationDTO.futureHops",target ="futureHops")
    @Mapping(source = "trackingInformationDTO.visitedHops",target ="visitedHops")
    ParcelEntity from(NewParcelInfoDTO newParcelInfoDTO, ParcelDTO parcelDTO, TrackingInformationDTO trackingInformationDTO);
}
