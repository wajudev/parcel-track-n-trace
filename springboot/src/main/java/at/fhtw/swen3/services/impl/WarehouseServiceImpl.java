package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.persistence.entities.WarehouseNextHopsEntity;
import at.fhtw.swen3.persistence.repositories.*;
import at.fhtw.swen3.services.WarehouseService;
import at.fhtw.swen3.services.validator.Validator;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@NoArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {
    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private GeoCoordinateRepository geoCoordinateRepository;
    @Autowired
    private WarehouseNextHopsRepository warehouseNextHopsRepository;

    @Autowired
    private HopRepository hopRepository;
    @Autowired
    private Validator validator;


    @Override
    public WarehouseEntity exportWarehouses() {
        return new WarehouseEntity();
    }

    @Override
    public void importWarehouses(WarehouseEntity warehouseEntity) {
        log.info("Importing data");
        recursiveImport(warehouseEntity);

    }

    private void recursiveImport(WarehouseEntity warehouseEntity) {
        if (warehouseEntity == null) {
            log.info("WareHouse is null");
        } else {
            for (WarehouseNextHopsEntity warehouseNextHop : warehouseEntity.getNextHops()) {
                log.warn("HEYYYYYY");
               if(warehouseNextHop.getHop() instanceof WarehouseEntity){
                    log.warn("OKAYYYYYYY");
                    log.warn(warehouseNextHop.getHop().getHopType());
                    recursiveImport((WarehouseEntity) warehouseNextHop.getHop());
               }
            }
            log.info("Validating warehouse with code " + warehouseEntity.getCode());
            validator.validate(warehouseEntity);
            geoCoordinateRepository.save(warehouseEntity.getLocationCoordinates());
            warehouseNextHopsRepository.saveAll(warehouseEntity.getNextHops());
            log.info(warehouseEntity.getHopType());
            warehouseRepository.save(warehouseEntity);
        }
    }

    @Override
    public HopEntity getWarehouse(String code) {
        return hopRepository.findByCode(code);
    }
}
