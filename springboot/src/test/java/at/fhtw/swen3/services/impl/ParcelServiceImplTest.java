package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.services.dto.TrackingInformation;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ParcelServiceImplTest {
    @Autowired
    private ParcelServiceImpl parcelService;

    static ParcelEntity parcel;
    final static RecipientEntity recipient = new RecipientEntity();
    final static RecipientEntity sender = new RecipientEntity();



    @BeforeAll
    static void init(){

        recipient.setCity("Vienna");
        recipient.setCountry("Austria");
        recipient.setStreet("Hoechstaedplatz 6");
        recipient.setPostalCode("A-1200");
        recipient.setName("Malte");


        sender.setCity("Vienna");
        sender.setName("Klaus");
        sender.setCountry("Austria");
        sender.setStreet("Gerhardusgasse 21");
        sender.setPostalCode("A-1200");

        HopArrivalEntity hopArrivalEntity = HopArrivalEntity.builder()
                .dateTime(OffsetDateTime.now())
                .code("ABCD12")
                .description("This is a description")
                .build();

        HopArrivalEntity hopArrivalEntity2 = HopArrivalEntity.builder()
                .dateTime(OffsetDateTime.now())
                .code("ABCD35")
                .description("This is a description 2")
                .build();

        parcel = ParcelEntity.builder()
                .weight(20f)
                .trackingId("PYJRB4HZ6")
                .recipient(recipient)
                .sender(sender)
                .state(TrackingInformation.StateEnum.PICKUP)
                .visitedHop(hopArrivalEntity)
                .futureHop(hopArrivalEntity2)
                .build();
    }


    @Test
    @Order(2)
    void insertParcel(){
        parcelService.submitParcel(parcel);
    }
    @Test
    @Order(3)
    void deliverParcel(){
        parcelService.reportParcelDelivery(parcel.getTrackingId());
    }
    @Test
    @Order(4)
    void deleteParcel(){
        parcelService.deleteParcelEntity(parcel.getTrackingId());
    }
    @Test
    @Order(5)
    void deleteRecipients(){
        parcelService.deleteHopArrivalEntity(parcel.getVisitedHops());
        parcelService.deleteHopArrivalEntity(parcel.getFutureHops());
        parcelService.deleteRecipientEntity(parcel.getRecipient().getName());
        parcelService.deleteRecipientEntity(parcel.getSender().getName());
    }
    @Test
    @Order(1)
    void insertRecipients(){
        parcelService.createRecipient(recipient);
        parcelService.createRecipient(sender);
    }

   /* @Test
    @Order(1)
    void insertRecipients(){
        parcelService.createRecipient(recipient);
        parcelService.createRecipient(sender);
        RecipientEntity foundRecipient = parcelService.findRecipient(recipient.getName());
        assertEquals(recipient.getName(),foundRecipient.getName());
        parcelService.deleteRecipientEntity(recipient.getName());
        assertNull(parcelService.findRecipient(recipient.getName()));

    }*/

}
