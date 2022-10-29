package at.fhtw.swen3.model.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Setter
@Getter
public class TruckEntity extends HopEntity {

    private String regionGeoJson;

    private String numberPlate;
}
