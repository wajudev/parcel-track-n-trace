package at.fhtw.swen3.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

@Entity
@Setter
@Getter
public class RecipientEntity {

    @Id
    @Column
    @Pattern(regexp = "\\w+")
    private String name;

    @Column
    @Pattern(regexp = "[A-Z]([a-z]?ß?)+\\s\\d*.*")
    private String street;

    @Column
    @Pattern(regexp = "[A]-\\d{4}")
    private String postalCode;

    @Column
    @Pattern(regexp = "[A-Z]([a-z]?ß?)+\\s*.*")
    private String city;

    @Column
    private String country;
}
