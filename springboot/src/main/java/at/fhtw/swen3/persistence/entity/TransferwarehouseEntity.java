package at.fhtw.swen3.persistence.entity;


import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class TransferwarehouseEntity extends HopEntity{

    @Column
    private String regionGeoJson;

    @Column
    private String logisticsPartner;

    @Column
    private String logisticsPartnerUrl;
}
