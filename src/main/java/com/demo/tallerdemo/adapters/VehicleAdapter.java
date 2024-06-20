package com.demo.tallerdemo.adapters;

import com.demo.tallerdemo.models.enums.CarType;
import com.demo.tallerdemo.services.VehicleService;
import com.demo.tallerdemo.services.impl.DieselVehicleServiceImpl;
import com.demo.tallerdemo.services.impl.ElectricVehicleServiceImpl;
import com.demo.tallerdemo.services.impl.GasolineVehicleServiceImpl;
import com.demo.tallerdemo.services.impl.VehicleServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class VehicleAdapter {

    private DieselVehicleServiceImpl dieselVehicleService;
    private GasolineVehicleServiceImpl gasolineVehicleService;
    private ElectricVehicleServiceImpl electricVehicleService;
    private VehicleServiceImpl vehicleService;

    public VehicleService getImpl (String carType) {
        if (CarType.DIESEL.getValue().equals(carType)) {
            return dieselVehicleService;
        } else if (CarType.ELECTRIC.getValue().equals(carType)) {
            return electricVehicleService;
        } else if (CarType.GASOLINE.getValue().equals(carType)) {
            return gasolineVehicleService;
        } else {
            return vehicleService;
        }
    }
}
