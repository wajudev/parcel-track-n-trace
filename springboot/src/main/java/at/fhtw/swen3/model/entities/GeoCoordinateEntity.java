package at.fhtw.swen3.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Setter
@Getter
public class GeoCoordinateEntity {
    @Id
    private Double lat;
    @Id
    private Double lon;
}
