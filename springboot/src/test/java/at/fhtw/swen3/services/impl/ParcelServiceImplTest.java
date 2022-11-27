package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ParcelServiceImplTest {
    @Autowired
    private ParcelServiceImpl parcelService;

    final static ParcelEntity parcel = new ParcelEntity();
    final static RecipientEntity recipient = new RecipientEntity();
    final static RecipientEntity sender = new RecipientEntity();

    //private static HopArrivalEntity hopArrivalEntity = new HopArrivalEntity();

    @BeforeAll
    static void init(){

        parcel.setWeight(20f);


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

        parcel.setRecipient(recipient);
        parcel.setSender(sender);


        /*hopArrivalEntity = new HopArrivalEntity();
        hopArrivalEntity.setCode("A-1200");
        hopArrivalEntity.setDescription("This is a description");
        hopArrivalEntity.setDateTime(OffsetDateTime.now());*/

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
    @Order(3)
    void deleteParcel(){
        parcelService.deleteParcelEntity(parcel.getTrackingId());
    }
    @Test
    @Order(5)
    void deleteRecipients(){
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
