package at.fhtw.swen3.persistence.entities;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressEntity {
    private String street;
    private String city;
    private String postalcode;
    private String country;

}
