package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.persistence.repositories.RecipientRepository;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.TrackingInformation;
import at.fhtw.swen3.services.validator.Validator;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@NoArgsConstructor
@Service
public class ParcelServiceImpl implements ParcelService {

    @Autowired
    private Validator validator;

    @Autowired
    private ParcelRepository parcelRepository;

    @Autowired
    private RecipientRepository recipientRepository;


    public List<ParcelEntity> getAllParcel() {
        return parcelRepository.findAll();
    }

    @Override
    public NewParcelInfo submitParcel(ParcelEntity newParcel) {
        log.info("Saving new Parcel in DB");
        validator.validate(newParcel);
        createRecipient(newParcel.getRecipient());
        createRecipient(newParcel.getSender());
        parcelRepository.save(newParcel);
        return dummyNewParcelInfo();
    }

    public TrackingInformation findParcel(String trackingId) {
        log.info("Searching for Parcel in DB");
        ParcelEntity entity = parcelRepository.findByTrackingId(trackingId);
        if(entity != null){
            TrackingInformation trackingInformation = new TrackingInformation();
            trackingInformation.setState(entity.getState());
            return trackingInformation;
        }
        return null;
    }

    private NewParcelInfo dummyNewParcelInfo() {
        return new NewParcelInfo()
                .trackingId("ABCDE6789");
    }

    @Override
    public void createRecipient(RecipientEntity recipient) {
        log.info("Saving new recipient in DB");
        recipientRepository.save(recipient);
    }
    @Override
    public RecipientEntity findRecipient(String name) {
        log.info("Looking for recipient with name: " + name + " in DB");
        return recipientRepository.findByName(name);
    }


    @Override
    public ParcelEntity getTrackingInformation(String trackingId) {
        return null;
    }

    @Override
    public void deleteEntity(String name) {
        recipientRepository.deleteByName(name);
    }

}
