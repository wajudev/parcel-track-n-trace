package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;

public interface WarehouseService {
    WarehouseEntity exportWarehouses();

    void importWarehouses(WarehouseEntity warehouseEntity);

    HopEntity getWarehouse(String code);

    void deleteAllWarehouses();
}
