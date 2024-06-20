package com.demo.tallerdemo.services.impl;

import com.demo.tallerdemo.models.dto.VehicleDto;
import com.demo.tallerdemo.models.enums.InjectionPump;
import com.demo.tallerdemo.models.pojo.DieselVehicle;
import com.demo.tallerdemo.repositories.DieselVehicleRepository;
import com.demo.tallerdemo.services.VehicleService;
import com.demo.tallerdemo.utils.VehicleBuilderUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DieselVehicleServiceImpl implements VehicleService {

    private DieselVehicleRepository dieselVehicleRepository;

    @Override
    public VehicleDto registerVehicle(VehicleDto vehicleDto) {
        DieselVehicle dieselVehicle = new DieselVehicle();
        dieselVehicle.setVin(vehicleDto.getVin());
        dieselVehicle.setRegistrationPlate(vehicleDto.getRegistrationPlate());
        Instant now = Instant.now();
        dieselVehicle.setCreatedDate(now);
        dieselVehicle.setUpdatedDate(now);
        dieselVehicle.setInjectionPump(InjectionPump.valueOf(vehicleDto.getInjectionPump()));
        dieselVehicle = dieselVehicleRepository.save(dieselVehicle);
        return VehicleBuilderUtil.buildVehicle(dieselVehicle);
    }

    @Override
    public VehicleDto reconvert(String vin, List<String> fuelTypes) {
        return null;
    }

    @Override
    public List<VehicleDto> getVehicles(Instant startDate, Instant endDate) {
        if (startDate != null) {

        }
        List<DieselVehicle> dieselVehicles = dieselVehicleRepository.searchAllByCreatedDateBetween(startDate, endDate);
        return dieselVehicles.stream().map(VehicleBuilderUtil::buildVehicle).toList();
    }

    @Override
    public VehicleDto getRegisterInformation(String vin) {
        Optional<DieselVehicle> dieselVehicleOpt = dieselVehicleRepository.findVehicleByVin(vin);
        if (dieselVehicleOpt.isEmpty()) {
            return null;
        }
        DieselVehicle dieselVehicle = dieselVehicleOpt.get();
        return VehicleBuilderUtil.buildVehicle(dieselVehicle);
    }
}
