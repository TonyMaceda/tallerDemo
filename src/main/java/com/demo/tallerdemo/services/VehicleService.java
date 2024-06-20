package com.demo.tallerdemo.services;

import com.demo.tallerdemo.models.dto.VehicleDto;

import java.time.Instant;
import java.util.List;

public interface VehicleService {

    VehicleDto registerVehicle (VehicleDto vehicleDto);

    VehicleDto reconvert (String vin, List<String> fuelTypes);

    List<VehicleDto> getVehicles (Instant startDate, Instant endDate);

    VehicleDto getRegisterInformation (String vin);
}
