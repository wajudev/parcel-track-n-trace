package at.fhtw.swen3.persistence.entity;

import at.fhtw.swen3.persistence.HopArrival;
import at.fhtw.swen3.persistence.Recipient;
import at.fhtw.swen3.persistence.TrackingInformation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ParcelEntity {
    @Min(value = 0, message = "Gewicht kann nicht < 0 sein")
    private Float weight;
    @NotNull
    private Recipient recipient;
    @NotNull
    private Recipient sender;
    private TrackingInformation.StateEnum state;
    @NotNull
    @Pattern(regexp = "^[A-Z]{4}\\d{1,4}$", message = "Falsches FutureHopPattern Pattern")
    private List<HopArrival> futureHops = new ArrayList<>();
    @NotNull
    @Pattern(regexp = "^[A-Z]{4}\\d{1,4}$", message = "Falsches trackingID Pattern")
    private List<HopArrival> visitedHops = new ArrayList<>();
    @Pattern(regexp = "^[A-Z0-9]{9}$", message = "Falsches trackingID Pattern")
    private String trackingId;
}
