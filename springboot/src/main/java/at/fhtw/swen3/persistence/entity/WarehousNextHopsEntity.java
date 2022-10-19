package at.fhtw.swen3.persistence.entity;

import javax.persistence.*;

@Entity
public class WarehousNextHopsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer traveltimeMins;

    @OneToOne
    private HopEntity hop;
}
