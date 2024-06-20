package com.demo.tallerdemo.resources;

import com.demo.tallerdemo.adapters.VehicleAdapter;
import com.demo.tallerdemo.models.dto.VehicleDto;
import com.demo.tallerdemo.models.request.VehicleRequest;
import com.demo.tallerdemo.models.response.VehicleRegisterInformationResponse;
import com.demo.tallerdemo.models.response.VehicleResponse;
import com.demo.tallerdemo.services.VehicleService;
import com.demo.tallerdemo.utils.ConversionUtils;
import com.demo.tallerdemo.utils.VehicleBuilderUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping(value = "/api/vehicles")
@AllArgsConstructor
public class VehicleResource {

    private VehicleAdapter vehicleAdapter;

    @PostMapping
    public ResponseEntity<VehicleResponse> registerVehicle (@RequestBody VehicleRequest vehicleRequest) {
        VehicleService vehicleService = vehicleAdapter.getImpl(vehicleRequest.getCarType());
        VehicleDto vehicleDto = vehicleService.registerVehicle(VehicleBuilderUtil.buildVehicleFromRequest(vehicleRequest));
        if (vehicleDto == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        VehicleResponse vehicleResponse = VehicleBuilderUtil.buildVehicleResponse(vehicleDto);
        return new ResponseEntity<>(vehicleResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<VehicleResponse>> getVehicles (@RequestParam(value = "carType", required = false) String carType, @RequestParam(value = "startDate", required = false) String startDate, @RequestParam(value = "endDate", required = false) String endDate) {
        VehicleService vehicleService = vehicleAdapter.getImpl(carType);
        Instant startInstant = ConversionUtils.convertStringStartToInstant(startDate);
        Instant endInstant = ConversionUtils.convertStringEndToInstant(endDate);
        List<VehicleDto> vehicles = vehicleService.getVehicles(startInstant, endInstant);
        List<VehicleResponse> response = vehicles.stream().map(VehicleBuilderUtil::buildVehicleResponse).toList();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/information")
    public ResponseEntity<VehicleRegisterInformationResponse> getVehicleRegisterInformation (@RequestParam("vin") String vin, @RequestParam("carType") String carType) {
        VehicleService vehicleService = vehicleAdapter.getImpl(carType);
        VehicleDto vehicleDto = vehicleService.getRegisterInformation(vin);
        if (vehicleDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        VehicleRegisterInformationResponse registerInformationResponse = VehicleBuilderUtil.buildVehicleRegisterInformationResponse(vehicleDto);
        return new ResponseEntity<>(registerInformationResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/reconvert")
    public ResponseEntity<VehicleResponse> reconvertVehicle (@RequestBody VehicleRequest vehicleRequest) {
        VehicleService vehicleService = vehicleAdapter.getImpl(vehicleRequest.getCarType());
        VehicleDto vehicleDto = vehicleService.reconvert(vehicleRequest.getVin(), vehicleRequest.getFuels());
        if (vehicleDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        VehicleResponse vehicleResponse = VehicleBuilderUtil.buildVehicleResponse(vehicleDto);
        return new ResponseEntity<>(vehicleResponse, HttpStatus.OK);
    }

}
