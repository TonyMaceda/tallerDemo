package com.demo.tallerdemo.services.impl;

import com.demo.tallerdemo.models.dto.VehicleDto;
import com.demo.tallerdemo.models.pojo.GasolineVehicle;
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
public class GasolineVehicleServiceImpl implements VehicleService {

    private GasolineVehicleRepository gasolineVehicleRepository;

    @Override
    public VehicleDto registerVehicle(VehicleDto vehicleDto) {
        GasolineVehicle gasolineVehicle = new GasolineVehicle();
        gasolineVehicle.setVin(vehicleDto.getVin());
        gasolineVehicle.setRegistrationPlate(vehicleDto.getRegistrationPlate());
        Instant now = Instant.now();
        gasolineVehicle.setCreatedDate(now);
        gasolineVehicle.setUpdatedDate(now);
        gasolineVehicle.setFuelTypes(StringUtils.collectionToDelimitedString(vehicleDto.getFuels(), ","));
        gasolineVehicle = gasolineVehicleRepository.save(gasolineVehicle);
        return VehicleBuilderUtil.buildVehicle(gasolineVehicle);
    }

    @Override
    public VehicleDto reconvert(String vin, List<String> fuelTypes) {
        return null;
    }

    @Override
    public List<VehicleDto> getVehicles(Instant startDate, Instant endDate) {
        List<GasolineVehicle> gasolineVehicles = gasolineVehicleRepository.searchAllByCreatedDateBetween(startDate, endDate);
        return gasolineVehicles.stream().map(VehicleBuilderUtil::buildVehicle).toList();
    }

    @Override
    public VehicleDto getRegisterInformation(String vin) {
        Optional<GasolineVehicle> gasolineVehicleOptional = gasolineVehicleRepository.findVehicleByVin(vin);
        if (gasolineVehicleOptional.isEmpty()) {
            return null;
        }
        GasolineVehicle gasolineVehicle = gasolineVehicleOptional.get();
        return VehicleBuilderUtil.buildVehicle(gasolineVehicle);
    }
}
