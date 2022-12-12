package at.fhtw.swen3.persistence.entities;


import at.fhtw.swen3.services.dto.TrackingInformation;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    @JoinColumn(name = "recipient_id")
    @ManyToOne(cascade = CascadeType.MERGE)
    private RecipientEntity recipient;

    @NotNull
    @JoinColumn(name = "sender_id")
    @ManyToOne(cascade = CascadeType.MERGE)
    private RecipientEntity sender;

    @Column
    @NotNull
    private TrackingInformation.StateEnum state;

    @Singular
    @JoinColumn(name = "hop_arrival_id")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @NotNull
    private List<HopArrivalEntity> futureHops = new ArrayList<>();

    @Singular
    @JoinColumn(name = "hop_arrival_id")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @NotNull
    private List<HopArrivalEntity> visitedHops = new ArrayList<>();

}
