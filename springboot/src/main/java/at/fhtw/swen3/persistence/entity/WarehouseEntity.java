package at.fhtw.swen3.persistence.entity;

import at.fhtw.swen3.services.dto.WarehouseNextHops;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NotNull
public class WarehouseEntity extends HopEntity{
    private Integer level;
    @OneToMany
    @NotNull
    private List<WarehouseNextHopsEntity> nextHops = new ArrayList<>();

}
