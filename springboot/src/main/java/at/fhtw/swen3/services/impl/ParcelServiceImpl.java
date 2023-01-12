package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.gps.service.GeoEncodingService;
import at.fhtw.swen3.gps.service.impl.OpenStreetMapEncodingProxy;
import at.fhtw.swen3.persistence.entities.*;
import at.fhtw.swen3.persistence.repositories.*;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.TrackingInformation;
import at.fhtw.swen3.services.dto.WarehouseNextHops;
import at.fhtw.swen3.services.mapper.RecipientMapper;
import at.fhtw.swen3.services.validator.Validator;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
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
    private WarehouseNextHopsRepository warehouseNextHopsRepository;

    @Autowired
    private TrackingInformationRepository trackingInformationRepository;

    @Autowired
    private TruckRepository truckRepository;
    @Autowired
    private WarehouseRepository warehouseRepository;

    private GeoEncodingService geoEncodingService = new OpenStreetMapEncodingProxy();



    public List<ParcelEntity> getAllParcel() {
        return parcelRepository.findAll();
    }

    @Override
    public NewParcelInfo submitParcel(ParcelEntity newParcel) {
        log.info("Creating unique tracking ID");
        String uniqueKey = uniqueID();
        newParcel.setTrackingId(String.valueOf(uniqueKey));
        newParcel.setState(TrackingInformation.StateEnum.PICKUP);

        // TODO: pathFinding(newParcel);

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
    private void pathFinding(ParcelEntity newParcel){
        GeoCoordinateEntity senderCoordinates = geoEncodingService.encodeAddress(
                RecipientMapper.INSTANCE.recipientEntityToAddressEntitiy(newParcel.getSender()));
        TruckEntity senderTruck = getNearestTruck(senderCoordinates);

        GeoCoordinateEntity recipientCoordinates = geoEncodingService.encodeAddress(
                RecipientMapper.INSTANCE.recipientEntityToAddressEntitiy(newParcel.getRecipient()));
        TruckEntity recipientTruck = getNearestTruck(recipientCoordinates);

        newParcel.getFutureHops().add(newHopArrivalEntity(senderTruck));
        recursiveCommonWarehouse(senderTruck,recipientTruck,newParcel);
        newParcel.getFutureHops().add(newHopArrivalEntity(recipientTruck));


    }

    private void recursiveCommonWarehouse(HopEntity sender, HopEntity recipient,ParcelEntity newParcel){
        WarehouseEntity senderParent =getParentWarehouse(sender);
        WarehouseEntity recipientParent = getParentWarehouse(recipient);
        if(senderParent.equals(recipientParent)){

        }else {
            newParcel.getFutureHops().add(newHopArrivalEntity(senderParent));

            recursiveCommonWarehouse(senderParent,recipientParent,newParcel);

            newParcel.getFutureHops().add(newHopArrivalEntity(recipientParent));

        }
    }

    private HopArrivalEntity newHopArrivalEntity(HopEntity hop){
        HopArrivalEntity hopArrivalEntity = new HopArrivalEntity();
        hopArrivalEntity.setCode(hop.getCode());
        hopArrivalEntity.setDescription(hop.getDescription());
        hopArrivalEntity.setDateTime(OffsetDateTime.now());
        return hopArrivalEntity;
    }

    private boolean hopExist(String code) {
        return hopRepository.existsByCode(code);
    }

    private WarehouseEntity getParentWarehouse(HopEntity hop){
        WarehouseNextHopsEntity warehouseNextHops = warehouseNextHopsRepository.findByHop(hop);
        List<WarehouseNextHopsEntity> warehouseNextHopsEntities = new ArrayList<>();
        warehouseNextHopsEntities.add(warehouseNextHops);
        return null; //TODO: warehouseRepository.findHop(hop.getId());
    }
    private TruckEntity getNearestTruck(GeoCoordinateEntity recipientCoordinates){
        List<TruckEntity> trucks = truckRepository.findAll();
        TruckEntity shortestDistanceTruck = new TruckEntity();
        double distance =0;
        for (TruckEntity truck: trucks) {
            double tmpDistance = getDistance(truck.getLocationCoordinates(),recipientCoordinates);
            if(tmpDistance < distance){
                shortestDistanceTruck = truck;
                distance = tmpDistance;
            }
        }
        return shortestDistanceTruck;
    }
    private double getDistance(GeoCoordinateEntity startPoint, GeoCoordinateEntity endPoint){
        return Math.sqrt(Math.pow(startPoint.getLat()-endPoint.getLat(),2)+
                Math.pow(startPoint.getLon()-endPoint.getLon(),2));
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
    @Transactional
    public void reportParcelHop(String trackingId, String code){
        validator.validate(trackingId);

        log.info("Searching for parcel with tracking ID: " + trackingId);
        ParcelEntity parcelEntity = parcelRepository.findByTrackingId(trackingId);
        if (!parcelEntity.getFutureHops().get(0).getCode().equals(code)){
            log.error("next hop of the parcel and hop code deosn't match");
        }

        switch (getHopType(code)){
            case "truck":
                log.info("Setting state to In Truck Delivery");
                parcelEntity.setState(TrackingInformation.StateEnum.INTRUCKDELIVERY);
            case "warehouse":
                log.info("Setting state to In Transport");
                parcelEntity.setState(TrackingInformation.StateEnum.INTRANSPORT);
            case "transferwarehouse":
                log.info("Setting state to Transferred");
                parcelEntity.setState(TrackingInformation.StateEnum.TRANSFERRED);
        }

        HopArrivalEntity hopArrival= parcelEntity.getFutureHops().remove(0);
        hopArrival.setDateTime(OffsetDateTime.now());

        parcelEntity.getVisitedHops().add(hopArrival);
        parcelRepository.save(parcelEntity);
    }

    protected String getHopType(String code){
        if (hopExist(code)) {
            return String.valueOf(hopRepository.getHopTypeByCode(code));
        }
        return null;
    }

    @Override
    public void deleteHopArrivalEntity(List<HopArrivalEntity> hops) {
        // TODO: Mach es genauer @Tom ;)
        hopArrivalRepository.deleteAllInBatch(hops);
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
