package at.fhtw.swen3.persistence.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@Table(name = "Truck")
public class TruckEntity extends HopEntity{

    private String regionGeoJson;

    private String numberPlate;
}
