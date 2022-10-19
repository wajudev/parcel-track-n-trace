package at.fhtw.swen3.persistence.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.OffsetDateTime;

@Entity
@Setter
@Getter
public class HopArrivalEntity {
    @Id
    private String code;

    private String description;

    private OffsetDateTime dateTime;

}
