package at.fhtw.swen3.persistence.entities;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.OffsetDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@Table(name = "hop_arrival")
public class HopArrivalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "code")
    @Pattern(regexp = "^[A-Z]{4}\\d{1,4}$", message = "Falsches FutureHopPattern Pattern")
    private String code;
    @Column(name = "description")
    private String description;


    @NotNull
    @Column(name = "dateTime")
    private OffsetDateTime dateTime;

}
