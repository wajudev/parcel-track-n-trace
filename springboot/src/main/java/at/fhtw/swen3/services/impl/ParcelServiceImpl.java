package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.persistence.repositories.RecipientRepository;
import at.fhtw.swen3.services.ParcelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class ParcelServiceImpl implements ParcelService {

    ParcelRepository parcelRepository;
    RecipientRepository recipientRepository;

    public ParcelServiceImpl(){
    }

    public ParcelServiceImpl(ParcelRepository parcelRepository, RecipientRepository recipientRepository) {
        this.parcelRepository = parcelRepository;
        this.recipientRepository = recipientRepository;
    }

    public List<ParcelEntity> getAllParcel(){
        return parcelRepository.findAll();
    }

    @Override
    public void submitParcel(ParcelEntity newParcel) {

    }

    @Override
    public ParcelEntity getTrackingInformation(String trackingId) {
        return null;
    }

}
