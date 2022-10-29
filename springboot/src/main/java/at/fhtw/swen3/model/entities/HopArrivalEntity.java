package at.fhtw.swen3.model.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.OffsetDateTime;

@Entity
@Setter
@Getter
public class HopArrivalEntity {
    @Id
    @Pattern(regexp = "^[A-Z]{4}\\d{1,4}$", message = "Falsches FutureHopPattern Pattern")
    private String code;

    private String description;

    @NotNull
    private OffsetDateTime dateTime;

}
