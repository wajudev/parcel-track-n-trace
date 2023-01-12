package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.Warehouse;

public interface WarehouseService {
    Warehouse exportWarehouses() throws Exception;

    void importWarehouses(WarehouseEntity warehouseEntity);

    Hop getWarehouse(String code);

    void deleteAllWarehouses();
}
