package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.persistence.entities.WarehouseNextHopsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseRepository extends JpaRepository<WarehouseEntity, Long> {
    //WarehouseEntity findByNextHops(List<WarehouseNextHopsEntity> warehouseNextHopsEntities);

    @Query(value =
            "SELECT * FROM hop WHERE hop.id = " +
                    "(SELECT hop_next_hops.warehouse_entity_id FROM hop_next_hops WHERE hop_next_hops.next_hops_id = " +
                    "(SELECT warehouse_next_hops.id FROM warehouse_next_hops" +
                    " WHERE warehouse_next_hops.hop_id = :hopId))",nativeQuery = true)
    WarehouseEntity findHop(@Param("hopId") Long id);
    List<WarehouseEntity> findByLevel(int level);
}
