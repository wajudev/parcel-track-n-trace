package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.persistence.entities.TrackingInformationEntity;
import at.fhtw.swen3.persistence.repositories.HopArrivalRepository;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.persistence.repositories.RecipientRepository;
import at.fhtw.swen3.persistence.repositories.TrackingInformationRepository;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.TrackingInformation;
import at.fhtw.swen3.services.validator.Validator;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;


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

    @Autowired
    private HopArrivalRepository hopArrivalRepository;

    @Autowired
    private TrackingInformationRepository trackingInformationRepository;



    public List<ParcelEntity> getAllParcel() {
        return parcelRepository.findAll();
    }

    @Override
    public NewParcelInfo submitParcel(ParcelEntity newParcel) {
        log.info("Creating unique tracking ID");
        String uniqueKey = uniqueID();
        log.info("Saving new Parcel in DB");
        validator.validate(newParcel);
        createRecipient(newParcel.getRecipient());
        createRecipient(newParcel.getSender());
        newParcel.setTrackingId(String.valueOf(uniqueKey));

        parcelRepository.save(newParcel);
        return new NewParcelInfo()
                .trackingId(String.valueOf(uniqueKey));
    }





    @Override
    public TrackingInformation trackParcel(String trackingId) {
        log.info("Searching for Parcel in DB");
        ParcelEntity entity = parcelRepository.findByTrackingId(trackingId);
        if (entity != null) {
            TrackingInformation trackingInformation = new TrackingInformation();
            trackingInformation.setState(entity.getState());
            log.info("Parcel with trackingId: "+trackingId+" found");
            return trackingInformation;
        }
        log.warn("Parcel with trackingId: "+ trackingId+ " could not be found in DB");
        return null;
    }


    @Override
    public void reportParcelDelivery(String trackingId) {

    }

    @Override
    public void reportParcelHop(String trackingId, String code) {

    }

    @Override
    public NewParcelInfo transitionParcel(ParcelEntity parcel) {
        validator.validate(parcel);

        return new NewParcelInfo()
                .trackingId(parcel.getTrackingId());
    }


    @Override
    public void saveHops(HopArrivalEntity hopArrivalEntity){
        log.info("Saving new hops in DB");
        hopArrivalRepository.save(hopArrivalEntity);
    }

    @Override
    public void saveTrackingInformation(TrackingInformationEntity trackingInformationEntity){
        log.info("Saving new tracking info in db ");
        trackingInformationRepository.save(trackingInformationEntity);
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

    public String uniqueID() {

        // create a string of uppercase and lowercase characters and numbers
        String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";

        // combine all strings
        String alphaNumeric = upperAlphabet + numbers;

        // create random string builder
        StringBuilder sb = new StringBuilder();

        // create an object of Random class
        Random random = new Random();

        // specify length of random string
        int length = 9;

        for (int i = 0; i < length; i++) {

            // generate random index number
            int index = random.nextInt(alphaNumeric.length());

            // get character specified by index
            // from the string
            char randomChar = alphaNumeric.charAt(index);

            // append the character to string builder
            sb.append(randomChar);
        }

        return sb.toString();
    }

}
