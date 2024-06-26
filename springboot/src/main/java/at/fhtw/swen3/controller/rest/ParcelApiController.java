package at.fhtw.swen3.controller.rest;


import at.fhtw.swen3.controller.ParcelApi;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.TrackingInformation;
import at.fhtw.swen3.services.mapper.ParcelMapper;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-22T22:59:08.396768Z[Etc/UTC]")
@Controller
@Slf4j
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

   // @Override
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/parcel",
            produces = { "application/json" },
            consumes = { "application/json" }
    )
    public ResponseEntity<NewParcelInfo> submitParcel(
            @Parameter(name = "Parcel", description = "", required = true)  @RequestBody Parcel parcel
    ){
        ParcelEntity parcelEntity = ParcelMapper.INSTANCE.map(parcel);

        NewParcelInfo newParcelInfo = parcelService.submitParcel(parcelEntity);

        return Optional.ofNullable(newParcelInfo)
                .map(value -> ResponseEntity.status(HttpStatus.CREATED).body(value))
                .orElse(ResponseEntity.notFound().build());
    }
    @Override
    public ResponseEntity<TrackingInformation> trackParcel(String trackingId) {
        System.out.println(trackingId);
        TrackingInformation trackingInformation = parcelService.trackParcel(trackingId);
        if(trackingInformation == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<TrackingInformation>(trackingInformation, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<NewParcelInfo> transitionParcel(String trackingId, Parcel parcel){
        ParcelEntity parcelEntity = ParcelMapper.INSTANCE.map(trackingId, parcel);
        NewParcelInfo newParcelInfo = parcelService.transitionParcel(parcelEntity);

        return new ResponseEntity<NewParcelInfo>(newParcelInfo, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> reportParcelHop(String trackingId, String code){
        parcelService.reportParcelHop(trackingId, code);
        return new ResponseEntity<>("Successfully reported hop", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> reportParcelDelivery(String trackingId){
        parcelService.reportParcelDelivery(trackingId);
        return new ResponseEntity<>("Successfully reported hop", HttpStatus.OK);
    }

}
