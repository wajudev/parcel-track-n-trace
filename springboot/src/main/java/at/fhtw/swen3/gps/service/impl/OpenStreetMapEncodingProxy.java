package at.fhtw.swen3.gps.service.impl;

import at.fhtw.swen3.gps.service.GeoEncodingService;
import at.fhtw.swen3.persistence.entities.AddressEntity;
import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
public class OpenStreetMapEncodingProxy implements GeoEncodingService {
    @Override
    public GeoCoordinateEntity encodeAddress(AddressEntity address) {
        HttpRequest request;
        try {
            URI uri = UriComponentsBuilder.newInstance()
                    .scheme("https")
                    .host("nominatim.openstreetmap.org")
                    .path("/search")
                    .queryParam("street", address.getStreet())
                    .queryParam("city", address.getCity())
                    .queryParam("postalcode", address.getPostalcode())
                    .queryParam("county", address.getCountry())
                    .queryParam("format", "geojson")
                    .build()
                    .encode()
                    .toUri();
            log.info(uri.toString());

            request = HttpRequest.newBuilder()
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .uri(uri)
                    .build();
        } catch (Exception exception){
            log.error(exception.toString());
            return null;
        }

        HttpResponse<String> response;
        try{
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException exception){
            log.error(exception.toString());
            return null;
        }

        log.info("HTTP " + response.statusCode() + ": " + response.body());

        try {
            var root = new ObjectMapper().readTree(response.body());
            var coordinates = root.get("features").get(0).get("geometry").get("coordinates");
            GeoCoordinateEntity result = GeoCoordinateEntity.builder()
                    .lat(coordinates.get(0).asDouble())
                    .lon(coordinates.get(1).asDouble())
                    .build();
            return result;
        } catch (JsonProcessingException exception) {
            log.error(exception.toString());
            return null;
        }
    }
}
