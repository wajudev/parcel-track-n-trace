package at.fhtw.swen3.persistence.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.OffsetDateTime;

@Entity
@Setter
@Getter
@Table(name = "HopArrival")
public class HopArrivalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Pattern(regexp = "^[A-Z]{4}\\d{1,4}$", message = "Falsches FutureHopPattern Pattern")
    private String code;

    private String description;

    @NotNull
    private OffsetDateTime dateTime;

}
