package at.fhtw.swen3.controller.rest;


import at.fhtw.swen3.controller.ParcelApi;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.mapper.ParcelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-22T22:59:08.396768Z[Etc/UTC]")
@Controller
public class ParcelApiController implements ParcelApi {
    @Autowired
    private NativeWebRequest request;

    @Autowired
    private ParcelService parcelService;

    @Autowired
    public ParcelApiController(NativeWebRequest request, ParcelService parcelService) {
        this.parcelService = parcelService;
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<NewParcelInfo> submitParcel(Parcel parcel){
        ParcelEntity parcelEntity = ParcelMapper.INSTANCE.map(parcel);

        NewParcelInfo newParcelInfo = parcelService.submitParcel(parcelEntity);

        return Optional.ofNullable(newParcelInfo)
                .map(value -> ResponseEntity.status(HttpStatus.CREATED).body(value))
                .orElse(ResponseEntity.notFound().build());
    }
}
