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
@Table(name = "parcel")
public class ParcelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    @Pattern(regexp = "^[A-Z0-9]{9}$", message = "Falsches trackingID Pattern")
    private String trackingId;

    @Column
    @DecimalMin(value = "0.1", message = "Gewicht kann nicht <= 0.0 sein")
    private Float weight;

    @NotNull
    @JoinColumn
    @ManyToOne
    private RecipientEntity recipient;

    @NotNull
    @JoinColumn(name = "sender_id")
    @ManyToOne
    private RecipientEntity sender;

    @Column
    private TrackingInformation.StateEnum state;

   // @NotNull
    @JoinColumn(name = "hop_arrival_id")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HopArrivalEntity> futureHops;

   // @NotNull
    @JoinColumn(name = "hop_arrival_id")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HopArrivalEntity> visitedHops;

}
