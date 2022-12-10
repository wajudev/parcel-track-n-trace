package at.fhtw.swen3.controller.rest;


import at.fhtw.swen3.controller.WarehouseApi;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.WarehouseService;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.Warehouse;
import at.fhtw.swen3.services.mapper.HopMapper;
import at.fhtw.swen3.services.mapper.WarehouseMapper;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-22T22:59:08.396768Z[Etc/UTC]")
@Controller
@Slf4j
public class WarehouseApiController implements WarehouseApi {

    @Autowired
    private NativeWebRequest request;

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    public WarehouseApiController(NativeWebRequest request, WarehouseService warehouseService) {
        this.request = request;
        this.warehouseService = warehouseService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Warehouse> exportWarehouses(){
        Warehouse warehouse = WarehouseMapper.INSTANCE.entityToDto(warehouseService.exportWarehouses());
        return ResponseEntity.of(Optional.ofNullable(warehouse));
    }

    @Override
    public ResponseEntity<Hop> getWarehouse(String code){
       return null;
    }

    @Override
    public ResponseEntity<Void> importWarehouses(Warehouse warehouse){
        WarehouseEntity warehouseEntity = WarehouseMapper.INSTANCE.dtoToEntity(warehouse);
        warehouseService.importWarehouses(warehouseEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
