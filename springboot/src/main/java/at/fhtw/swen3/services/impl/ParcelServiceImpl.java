package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.*;
import at.fhtw.swen3.persistence.repositories.*;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.TrackingInformation;
import at.fhtw.swen3.services.validator.Validator;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;


@Slf4j
@NoArgsConstructor
@Service
@Transactional
public class ParcelServiceImpl implements ParcelService {


    @Autowired
    private Validator validator;

    @Autowired
    private ParcelRepository parcelRepository;

    @Autowired
    private RecipientRepository recipientRepository;

    @Autowired
    private HopRepository hopRepository;

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
        newParcel.setTrackingId(String.valueOf(uniqueKey));
        newParcel.setState(TrackingInformation.StateEnum.PICKUP);

        return saveParcel(newParcel);
    }

    @Override
    public NewParcelInfo transitionParcel(ParcelEntity parcel) {
        log.info("Changing State to transfered");
        parcel.setState(TrackingInformation.StateEnum.TRANSFERRED);
        return saveParcel(parcel);
    }

    private NewParcelInfo saveParcel(ParcelEntity newParcel){
        log.info("Validating parcel");
        validator.validate(newParcel);


        createRecipient(newParcel.getRecipient());
        createRecipient(newParcel.getSender());

        log.info("Saving new Parcel in DB");
        parcelRepository.save(newParcel);

        return new NewParcelInfo()
                .trackingId(String.valueOf(newParcel.getTrackingId()));
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
    public ParcelEntity reportParcelDelivery(String trackingId) {
        ParcelEntity parcelEntity = parcelRepository.findByTrackingId(trackingId);
        if (parcelEntity != null){
            return changeTrackingStateToDelivered(parcelEntity);
        }
        return null;
    }

    private ParcelEntity changeTrackingStateToDelivered(ParcelEntity parcelEntity){
        parcelEntity.setState(TrackingInformation.StateEnum.DELIVERED);
        parcelRepository.save(parcelEntity);
        return parcelEntity;
    }

    @Override
    public ParcelEntity reportParcelHop(String trackingId, String code) {
        ParcelEntity parcelEntity = parcelRepository.findByTrackingId(trackingId);

        HopEntity hopEntity = hopRepository.findByCode(code);

        if (parcelEntity != null && hopEntity != null){
            HopArrivalEntity hopArrivalEntity = HopArrivalEntity.builder()
                    .dateTime(OffsetDateTime.now())
                    .code(hopEntity.getCode())
                    .description(hopEntity.getDescription())
                    .build();
            parcelEntity.getVisitedHops().add(hopArrivalEntity);
            parcelRepository.save(parcelEntity);
            return parcelEntity;
        }
        return null;
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
    public void deleteRecipientEntity(String name) {
        recipientRepository.deleteByName(name);
    }

    @Override
    public void deleteParcelEntity(String trackingId) {
        parcelRepository.deleteByTrackingId(trackingId);
    }

    public String uniqueID() {
        String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";
        String alphaNumeric = upperAlphabet + numbers;
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 9;
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alphaNumeric.length());
            char randomChar = alphaNumeric.charAt(index);
            sb.append(randomChar);
        }
        return sb.toString();
    }
}
