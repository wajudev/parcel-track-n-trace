package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.*;
import at.fhtw.swen3.persistence.repositories.*;
import at.fhtw.swen3.services.WarehouseService;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.Warehouse;
import at.fhtw.swen3.services.mapper.HopMapper;
import at.fhtw.swen3.services.mapper.WarehouseMapper;
import at.fhtw.swen3.services.validator.Validator;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@NoArgsConstructor
@Transactional
public class WarehouseServiceImpl implements WarehouseService {
    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private GeoCoordinateRepository geoCoordinateRepository;
    @Autowired
    private WarehouseNextHopsRepository warehouseNextHopsRepository;

    @Autowired
    private TransferwarehouseRepository transferwarehouseRepository;

    @Autowired
    private TruckRepository truckRepository;

    @Autowired
    private HopRepository hopRepository;
    @Autowired
    private Validator validator;


    @Override

    public Warehouse exportWarehouses() throws Exception{
        List<WarehouseEntity> entities = warehouseRepository.findByLevel(0);
        if (entities.isEmpty()){
            // TODO throw right Exceptions
            throw new Exception("No loaded hierarchy");
        }
        return WarehouseMapper.INSTANCE.entityToDto(entities.get(0));
    }

    @Override
    public void importWarehouses(WarehouseEntity warehouseEntity) {
        log.info("Deleting old entries");
        deleteAllWarehouses();
        log.info("Importing data");
        recursiveImport(warehouseEntity);

    }

    private void recursiveImport(WarehouseEntity warehouseEntity) {
        if (warehouseEntity == null) {
            log.info("WareHouse is null");
        } else {
            for (WarehouseNextHopsEntity warehouseNextHop : warehouseEntity.getNextHops()) {
               if(warehouseNextHop.getHop() instanceof WarehouseEntity){
                    log.warn(warehouseNextHop.getHop().getHopType());
                    recursiveImport((WarehouseEntity) warehouseNextHop.getHop());
               }
               if(warehouseNextHop.getHop() instanceof TruckEntity){
                   geoCoordinateRepository.save(warehouseNextHop.getHop().getLocationCoordinates());
                   truckRepository.save((TruckEntity)warehouseNextHop.getHop());
               }
               if(warehouseNextHop.getHop() instanceof TransferwarehouseEntity){
                   geoCoordinateRepository.save(warehouseNextHop.getHop().getLocationCoordinates());
                   transferwarehouseRepository.save((TransferwarehouseEntity) warehouseNextHop.getHop());
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
    public Hop getWarehouse(String code) {
        // TODO Exceptions & guard clauses
        HopEntity hopEntity = hopRepository.findByCode(code);
        return HopMapper.INSTANCE.entityToDto(hopEntity);
    }

    @Override
    public void deleteAllWarehouses() {
        hopRepository.deleteAll();
        warehouseNextHopsRepository.deleteAll();
    }
}
