package at.fhtw.swen3.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GeorCoordinateEntity {
    @Id
    private Double lat;
    @Id
    private Double lon;
}
