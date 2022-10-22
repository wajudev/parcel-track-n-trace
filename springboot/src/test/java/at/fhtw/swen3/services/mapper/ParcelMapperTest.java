package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.services.dto.*;
import at.fhtw.swen3.persistence.entity.ParcelEntity;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ParcelMapperTest {
    private static final Logger log = LoggerFactory.getLogger(ParcelMapperTest.class);

    @Test
    void dtoToEntity() {
        log.info("TEST dtoToEntity");

        final Parcel parcelDTO = new Parcel();
        parcelDTO.setWeight(20f);

        final Recipient recipient = new Recipient();
        recipient.setCity("Vienna");
        recipient.setCountry("Austria");
        recipient.setStreet("Höchstädplatz");
        recipient.setPostalCode("1200");

        final Recipient sender = new Recipient();
        sender.setCity("Vienna");
        sender.setCountry("Austria");
        sender.setStreet("Gerhardusgasse");
        sender.setPostalCode("1200");

        parcelDTO.setRecipient(recipient);
        parcelDTO.setSender(sender);

        final NewParcelInfo newParcelInfoDTO = new NewParcelInfo();
        newParcelInfoDTO.setTrackingId("test5");

        final TrackingInformation trackingInformationDTO = new TrackingInformation();
        final List<HopArrival> visitedHops = new ArrayList<>();
        visitedHops.add(new HopArrival());
        visitedHops.add(new HopArrival());
        visitedHops.add(new HopArrival());
        final List<HopArrival> futureHops = new ArrayList<>();
        futureHops.add(new HopArrival());
        futureHops.add(new HopArrival());

        trackingInformationDTO.setVisitedHops(visitedHops);
        trackingInformationDTO.setFutureHops(futureHops);

        final ParcelMapper mapper = ParcelMapper.INSTANCE;
        ParcelEntity entity;
        entity=mapper.from(newParcelInfoDTO,parcelDTO,trackingInformationDTO);

        assertEquals(parcelDTO.getWeight(), entity.getWeight());
        assertEquals(newParcelInfoDTO.getTrackingId(), entity.getTrackingId());


    }
}