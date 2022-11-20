package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.services.impl.ParcelServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class ParcelServiceImplTest {
    @Autowired
    private ParcelServiceImpl parcelService;

    final static ParcelEntity parcel = new ParcelEntity();
    final static RecipientEntity recipient = new RecipientEntity();
    final static RecipientEntity sender = new RecipientEntity();

    @BeforeAll
    static void init(){

        parcel.setWeight(20f);


        recipient.setCity("Vienna");
        recipient.setCountry("Austria");
        recipient.setStreet("Hoechstaedplatz 6");
        recipient.setPostalCode("A-1200");
        recipient.setName("Malte");


        sender.setCity("Vienna");
        sender.setCountry("Austria");
        sender.setStreet("Gerhardusgasse 21");
        sender.setPostalCode("A-1200");

        parcel.setRecipient(recipient);
        parcel.setSender(sender);

        parcel.setTrackingId("QWERTZUIO");

        final List<HopArrivalEntity> visitedHops = new ArrayList<>();
        visitedHops.add(new HopArrivalEntity());
        visitedHops.add(new HopArrivalEntity());
        visitedHops.add(new HopArrivalEntity());
        final List<HopArrivalEntity> futureHops = new ArrayList<>();
        futureHops.add(new HopArrivalEntity());
        futureHops.add(new HopArrivalEntity());

        parcel.setVisitedHops(visitedHops);
        parcel.setFutureHops(futureHops);
    }


    @Test
    void insertParcel(){


        parcelService.createRecipient(recipient);
        parcelService.createRecipient(sender);

        parcelService.submitParcel(parcel);
    }
    //@Test
    void insertRecipient(){
        parcelService.createRecipient(recipient);
        RecipientEntity foundRecipient = parcelService.findRecipient(recipient.getName());
        assertEquals(recipient.getName(),foundRecipient.getName());
        parcelService.deleteEntity(recipient.getName());
        assertNull(parcelService.findRecipient(recipient.getName()));

    }

}
