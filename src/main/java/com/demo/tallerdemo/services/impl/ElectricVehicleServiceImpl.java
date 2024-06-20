package com.demo.tallerdemo.services.impl;

import com.demo.tallerdemo.models.dto.VehicleDto;
import com.demo.tallerdemo.models.enums.Battery;
import com.demo.tallerdemo.models.pojo.ElectricVehicle;
import com.demo.tallerdemo.models.pojo.GasolineVehicle;
import com.demo.tallerdemo.repositories.ElectricVehicleRepository;
import com.demo.tallerdemo.repositories.GasolineVehicleRepository;
import com.demo.tallerdemo.services.VehicleService;
import com.demo.tallerdemo.utils.VehicleBuilderUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ElectricVehicleServiceImpl implements VehicleService {

    private ElectricVehicleRepository electricVehicleRepository;
    private GasolineVehicleRepository gasolineVehicleRepository;

    @Override
    public VehicleDto registerVehicle(VehicleDto vehicleDto) {
        if (!StringUtils.hasLength(vehicleDto.getVin()) || !StringUtils.hasLength(vehicleDto.getRegistrationPlate())) {
            return null;
        }
        if (!StringUtils.hasLength(vehicleDto.getBattery()) || StringUtils.hasLength(vehicleDto.getCurrent()) || StringUtils.hasLength(vehicleDto.getVoltage())) {
            return null;
        }
        ElectricVehicle electricVehicle = new ElectricVehicle();
        electricVehicle.setVin(vehicleDto.getVin());
        electricVehicle.setRegistrationPlate(vehicleDto.getRegistrationPlate());
        Instant now = Instant.now();
        electricVehicle.setCreatedDate(now);
        electricVehicle.setUpdatedDate(now);
        electricVehicle.setBattery(Battery.valueOf(vehicleDto.getBattery()));
        electricVehicle.setVoltage(vehicleDto.getVoltage());
        electricVehicle.setCurrent(vehicleDto.getCurrent());
        electricVehicle = electricVehicleRepository.save(electricVehicle);
        return VehicleBuilderUtil.buildVehicle(electricVehicle);
    }

    @Override
    public VehicleDto reconvert(String vin, List<String> fuelTypes) {
        Optional<ElectricVehicle> electricVehicleOptional = electricVehicleRepository.findVehicleByVin(vin);
        if (electricVehicleOptional.isEmpty() || fuelTypes == null || fuelTypes.isEmpty()) {
            return null;
        }
        ElectricVehicle electricVehicle = electricVehicleOptional.get();
        GasolineVehicle gasolineVehicle = new GasolineVehicle();
        gasolineVehicle.setId(electricVehicle.getId());
        gasolineVehicle.setVin(electricVehicle.getVin());
        gasolineVehicle.setRegistrationPlate(electricVehicle.getRegistrationPlate());
        gasolineVehicle.setCreatedDate(electricVehicle.getCreatedDate());
        gasolineVehicle.setUpdatedDate(Instant.now());
        String newFuelTypes = StringUtils.collectionToDelimitedString(fuelTypes, ",");
        gasolineVehicle.setFuelTypes(newFuelTypes);
        electricVehicleRepository.delete(electricVehicle);
        gasolineVehicle = gasolineVehicleRepository.save(gasolineVehicle);
        return VehicleBuilderUtil.buildVehicle(gasolineVehicle);
    }

    @Override
    public List<VehicleDto> getVehicles(Instant startDate, Instant endDate) {
        List<ElectricVehicle> electricVehicles = electricVehicleRepository.searchAllByCreatedDateBetween(startDate, endDate);
        return electricVehicles.stream().map(VehicleBuilderUtil::buildVehicle).toList();
    }

    @Override
    public VehicleDto getRegisterInformation(String vin) {
        Optional<ElectricVehicle> electricVehicleOptional = electricVehicleRepository.findVehicleByVin(vin);
        if (electricVehicleOptional.isEmpty()) {
            return null;
        }
        ElectricVehicle electricVehicle = electricVehicleOptional.get();
        return VehicleBuilderUtil.buildVehicle(electricVehicle);
    }
}
