package at.fhtw.swen3.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Setter
@Getter
@Table(name = "recipient")
public class RecipientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

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
