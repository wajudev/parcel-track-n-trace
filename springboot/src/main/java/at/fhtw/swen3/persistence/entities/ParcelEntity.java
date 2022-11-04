package at.fhtw.swen3.persistence.entities;


import at.fhtw.swen3.services.dto.TrackingInformation;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table
public class ParcelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    @Pattern(regexp = "^[A-Z0-9]{9}$", message = "Falsches trackingID Pattern")
    private String trackingId;

    @DecimalMin(value = "0.0", message = "Gewicht kann nicht < 0.0 sein")
    private Float weight;

    @NotNull
    @Column
    @JoinColumn
    @OneToOne
    private RecipientEntity recipient;

    @NotNull
    @Column
    @JoinColumn
    @OneToOne
    private RecipientEntity sender;
    private TrackingInformation.StateEnum state;

    @NotNull
    @Column
    @OneToMany
    private List<HopArrivalEntity> futureHops = new ArrayList<>();

    @NotNull
    @Column
    @OneToMany
    private List<HopArrivalEntity> visitedHops = new ArrayList<>();

}
