package com.demo.tallerdemo.services.impl;

import com.demo.tallerdemo.models.dto.VehicleDto;
import com.demo.tallerdemo.models.pojo.Vehicle;
import com.demo.tallerdemo.repositories.VehicleRepository;
import com.demo.tallerdemo.services.VehicleService;
import com.demo.tallerdemo.utils.VehicleBuilderUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private VehicleRepository vehicleRepository;

    @Override
    public VehicleDto registerVehicle(VehicleDto vehicleDto) {
        return null;
    }

    @Override
    public VehicleDto reconvert(String vin, List<String> fuelTypes) {
        return null;
    }

    @Override
    public List<VehicleDto> getVehicles(Instant startDate, Instant endDate) {
        List<Vehicle> vehicles = vehicleRepository.searchAllByCreatedDateBetween(startDate, endDate);
        return vehicles.stream().map(VehicleBuilderUtil::buildVehicle).toList();
    }

    @Override
    public VehicleDto getRegisterInformation(String vin) {
        return null;
    }
}
