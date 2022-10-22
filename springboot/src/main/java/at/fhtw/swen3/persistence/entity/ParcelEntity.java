package at.fhtw.swen3.persistence.entity;

import at.fhtw.swen3.services.dto.HopArrival;
import at.fhtw.swen3.services.dto.Recipient;
import at.fhtw.swen3.services.dto.TrackingInformation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class ParcelEntity {

    @Id
    @Column
    @Pattern(regexp = "^[A-Z0-9]{9}$", message = "Falsches trackingID Pattern")
    private String trackingId;

    @Min(value = 0, message = "Gewicht kann nicht < 0 sein")
    private Float weight;

    @NotNull
    @Column
    @OneToOne
    private RecipientEntity recipient;

    @NotNull
    @Column
    @OneToOne
    private RecipientEntity sender;
    private TrackingInformation.StateEnum state;

    @NotNull
   // @Pattern(regexp = "^[A-Z]{4}\\d{1,4}$", message = "Falsches FutureHopPattern Pattern")
    @Column
    @OneToMany
    private List<HopArrivalEntity> futureHops = new ArrayList<>();

    @NotNull
    @Column
    @OneToMany
   // @Pattern(regexp = "^[A-Z]{4}\\d{1,4}$", message = "Falsches VisitedHopPattern Pattern")
    private List<HopArrivalEntity> visitedHops = new ArrayList<>();

}
