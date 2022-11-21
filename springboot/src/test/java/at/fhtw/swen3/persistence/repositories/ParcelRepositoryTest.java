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

import java.util.ArrayList;
import java.util.List;


@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class ParcelRepositoryTest {

    @Autowired
    private ParcelRepository parcelRepository;
    final static ParcelEntity parcel = new ParcelEntity();
    final static RecipientEntity recipient = new RecipientEntity();
    final static RecipientEntity sender = new RecipientEntity();

    @BeforeAll
    static void init(){

        parcel.setWeight(20f);


        recipient.setCity("Vienna");
        recipient.setCountry("Austria");
        recipient.setStreet("Höchstädplatz");
        recipient.setPostalCode("1200");


        sender.setCity("Vienna");
        sender.setCountry("Austria");
        sender.setStreet("Gerhardusgasse");
        sender.setPostalCode("1200");

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
        parcelRepository.save(parcel);
    }

}
