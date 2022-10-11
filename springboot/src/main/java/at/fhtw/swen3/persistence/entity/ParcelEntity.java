package at.fhtw.swen3.persistence.entity;

import at.fhtw.swen3.persistence.HopArrival;
import at.fhtw.swen3.persistence.Recipient;
import at.fhtw.swen3.persistence.TrackingInformation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ParcelEntity {
    private Float weight;
    private Recipient recipient;
    private Recipient sender;
    private TrackingInformation.StateEnum state;
    private List<HopArrival> futureHops = new ArrayList<>();
    private List<HopArrival> visitedHops = new ArrayList<>();
    private String trackingId;
}
