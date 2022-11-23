package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.TrackingInformation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;
public interface ParcelService {

    List<ParcelEntity> getAllParcel();

    NewParcelInfo submitParcel(ParcelEntity newParcel);

    void createRecipient(RecipientEntity recipient);

    RecipientEntity findRecipient(String name);

    ParcelEntity getTrackingInformation(String trackingId);

    void deleteEntity(String name);

    TrackingInformation findParcel(String trackingId);

}
