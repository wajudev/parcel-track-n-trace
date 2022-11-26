package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.persistence.entities.TrackingInformationEntity;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.TrackingInformation;

import java.util.List;
import java.util.Optional;

public interface ParcelService {

    List<ParcelEntity> getAllParcel();

    NewParcelInfo submitParcel(ParcelEntity newParcel);

    NewParcelInfo transitionParcel(ParcelEntity parcel);

    void saveHops(HopArrivalEntity hopArrivalEntity);

    void saveTrackingInformation(TrackingInformationEntity trackingInformationEntity);

    void createRecipient(RecipientEntity recipient);

    RecipientEntity findRecipient(String name);

    ParcelEntity getTrackingInformation(String trackingId);

    void deleteEntity(String name);

    TrackingInformation trackParcel(String trackingId);

    ParcelEntity reportParcelDelivery(String trackingId);
    ParcelEntity reportParcelHop(String trackingId, String code);


}
