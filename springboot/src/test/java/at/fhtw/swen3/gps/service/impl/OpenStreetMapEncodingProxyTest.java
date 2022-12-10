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
        // TODO: java.lang.NullPointerException: Cannot invoke
        //  "com.fasterxml.jackson.databind.JsonNode.get(String)"
        //  because the return value of "com.fasterxml.jackson.databind.JsonNode.get(int)" is null
        var result = geoEncodingService.encodeAddress(address);

        assertNotNull(result);
        assertEquals(48.239470, result.getLat());
        assertEquals(16.378000, result.getLon());
    }
}