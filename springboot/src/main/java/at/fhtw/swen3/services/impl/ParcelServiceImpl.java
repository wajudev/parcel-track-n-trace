package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.persistence.repositories.RecipientRepository;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.mapper.ParcelMapper;
import at.fhtw.swen3.services.validator.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Validation;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
public class ParcelServiceImpl implements ParcelService {

    private final ParcelRepository parcelRepository;
    private final RecipientRepository recipientRepository;
    private final Validator validator;


    public List<ParcelEntity> getAllParcel() {
        return parcelRepository.findAll();
    }

    @Override
    public boolean submitParcel(ParcelEntity newParcel) {
        try {
            log.info("Validating new parcel");
            validator.validate(newParcel);
            log.info("Saving new parcel in DB");
            parcelRepository.save(newParcel);
            return true;
        }catch (Exception e){
            return false;
        }

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
