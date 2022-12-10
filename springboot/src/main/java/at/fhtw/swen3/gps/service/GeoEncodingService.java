package at.fhtw.swen3.gps.service;

import at.fhtw.swen3.persistence.entities.AddressEntity;
import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import org.apache.tomcat.jni.Address;

public interface GeoEncodingService {
    GeoCoordinateEntity encodeAddress(AddressEntity address);
}
