package at.fhtw.swen3.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NotNull
@Table(name = "Warehouse")
public class WarehouseEntity extends HopEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private Integer level;

    @OneToMany
    @NotNull
    private List<WarehouseNextHopsEntity> nextHops = new ArrayList<>();

    @Column
    private String hop;
}
