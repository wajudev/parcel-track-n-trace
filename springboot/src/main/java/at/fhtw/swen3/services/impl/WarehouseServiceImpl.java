package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.persistence.repositories.HopRepository;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.persistence.repositories.RecipientRepository;
import at.fhtw.swen3.persistence.repositories.WarehouseRepository;
import at.fhtw.swen3.services.WarehouseService;
import at.fhtw.swen3.services.validator.Validator;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@NoArgsConstructor
public class  WarehouseServiceImpl implements WarehouseService {
    @Autowired
    private  WarehouseRepository warehouseRepository;
    @Autowired
    private  RecipientRepository recipientRepository;
    @Autowired
    private HopRepository hopRepository;
    @Autowired
    private  Validator validator;


    @Override
    public WarehouseEntity exportWarehouses() {
        return new WarehouseEntity();
    }

    @Override
    public void importWarehouses(WarehouseEntity warehouseEntity) {
        validator.validate(warehouseEntity);
        warehouseRepository.save(warehouseEntity);
    }

    @Override
    public HopEntity getWarehouse(String code) {
        return hopRepository.findByCode(code);
    }
}
