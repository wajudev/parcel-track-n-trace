package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.persistence.entities.TrackingInformationEntity;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.TrackingInformation;

import java.util.List;
public interface ParcelService {

    List<ParcelEntity> getAllParcel();

    NewParcelInfo submitParcel(ParcelEntity newParcel);

    void saveHops(HopArrivalEntity hopArrivalEntity);

    void saveTrackingInformation(TrackingInformationEntity trackingInformationEntity);

    void createRecipient(RecipientEntity recipient);

    RecipientEntity findRecipient(String name);

    ParcelEntity getTrackingInformation(String trackingId);

    void deleteEntity(String name);

    TrackingInformation findParcel(String trackingId);

    void reportParcelDelivery(String trackingId);
    void reportParcelHop(String trackingId, String code);

    NewParcelInfo transitionParcel(ParcelEntity parcel);

}
