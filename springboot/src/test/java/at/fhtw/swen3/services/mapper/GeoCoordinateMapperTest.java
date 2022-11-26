package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.services.dto.*;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class GeoCoordinateMapperTest {
    private static final Logger log = LoggerFactory.getLogger(GeoCoordinateMapperTest.class);

    @Test
    void entityToDto() {
        final GeoCoordinateEntity geoCoordinateEntity = new GeoCoordinateEntity();
        geoCoordinateEntity.setLat(10.33334);
        geoCoordinateEntity.setLon(-38.976);

        final GeoCoordinateMapper mapper = GeoCoordinateMapper.INSTANCE;

        final GeoCoordinate geoCoordinateDto = mapper.entityToDto(geoCoordinateEntity);

        assertEquals(geoCoordinateEntity.getLat(),geoCoordinateDto.getLat());
        assertEquals(geoCoordinateEntity.getLon(),geoCoordinateDto.getLon());

    }
}
