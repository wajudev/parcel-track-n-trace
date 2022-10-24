package at.fhtw.swen3.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class WarehouseEntity extends HopEntity{
    private Integer level;
    @OneToMany
    private List<WarehouseNextHopsEntity> nextHops = new ArrayList<>();
}
