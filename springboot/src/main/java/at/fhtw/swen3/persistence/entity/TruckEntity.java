package at.fhtw.swen3.persistence.entity;


import javax.persistence.Entity;

@Entity
public class TruckEntity extends HopEntity{

    private String regionGeoJson;

    private String numberPlate;
}
