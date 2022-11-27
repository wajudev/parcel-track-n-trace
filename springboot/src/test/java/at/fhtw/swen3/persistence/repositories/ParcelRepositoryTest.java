package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.services.dto.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class ParcelRepositoryTest {

    @Autowired
    private ParcelRepository parcelRepository;
    private static ParcelEntity parcelEntity = new ParcelEntity();
    private static RecipientEntity recipient = new RecipientEntity();
    private static RecipientEntity sender = new RecipientEntity();

    //private static HopArrivalEntity hopArrivalEntity = new HopArrivalEntity();

    @BeforeAll
    static void init(){

        parcelEntity.setWeight(20f);


        recipient.setCity("Vienna");
        recipient.setCountry("Austria");
        recipient.setStreet("Hoechstaedplatz");
        recipient.setPostalCode("A-1200");
        recipient.setName("Noah");


        sender.setCity("Vienna");
        sender.setCountry("Austria");
        sender.setStreet("Gerhardusgasse 21");
        sender.setPostalCode("A-1200");
        sender.setName("Malte");


        parcelEntity.setRecipient(recipient);
        parcelEntity.setSender(sender);

        parcelEntity.setTrackingId("QWERTZUIO");

       /* hopArrivalEntity = new HopArrivalEntity();
        hopArrivalEntity.setCode("A-1200");
        hopArrivalEntity.setDescription("This is a description");
        hopArrivalEntity.setDateTime(OffsetDateTime.now());*/
    }

   // @Test
    void insertParcel(){
        // Create sender and recipient before saving!

        ParcelEntity parcel = parcelRepository.save(parcelEntity);
        assertEquals(parcel.getWeight(), parcelEntity.getWeight());
    }

}
