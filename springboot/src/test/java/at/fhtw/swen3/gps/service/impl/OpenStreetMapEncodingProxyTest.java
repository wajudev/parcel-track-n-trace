package at.fhtw.swen3.gps.service.impl;

import at.fhtw.swen3.persistence.entities.AddressEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OpenStreetMapEncodingProxyTest {

    @Test
    void encodeAddress() {
        var geoEncodingService = new OpenStreetMapEncodingProxy();
        var address = AddressEntity.builder()
                .street("Höchstädtplatz 6")
                .postalcode("1200")
                .city("Vienna")
                .country("Austria")
                .build();
        var result = geoEncodingService.encodeAddress(address);

        assertNotNull(result);
        assertEquals(16.3774409, result.getLat());
        assertEquals(48.2391664, result.getLon());
    }
}