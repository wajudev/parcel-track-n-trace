package at.fhtw.swen3.services.dto;

import at.fhtw.swen3.persistence.Recipient;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParcelDTO {
    private Float weight;
    private Recipient recipient;
    private Recipient sender;
}
