package com.demo.tallerdemo.utils;

import com.demo.tallerdemo.models.dto.VehicleDto;
import com.demo.tallerdemo.models.enums.Battery;
import com.demo.tallerdemo.models.enums.CarType;
import com.demo.tallerdemo.models.enums.FuelType;
import com.demo.tallerdemo.models.enums.InjectionPump;
import com.demo.tallerdemo.models.pojo.DieselVehicle;
import com.demo.tallerdemo.models.pojo.ElectricVehicle;
import com.demo.tallerdemo.models.pojo.GasolineVehicle;
import com.demo.tallerdemo.models.pojo.Vehicle;
import com.demo.tallerdemo.models.request.VehicleRequest;
import com.demo.tallerdemo.models.response.*;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class VehicleBuilderUtil {

    public static VehicleDto buildVehicle (Vehicle vehicle) {
        if (vehicle instanceof ElectricVehicle) {
            return buildVehicle(((ElectricVehicle) vehicle));
        } else if (vehicle instanceof DieselVehicle) {
            return buildVehicle(((DieselVehicle) vehicle));
        } else if (vehicle instanceof GasolineVehicle) {
            return buildVehicle(((GasolineVehicle) vehicle));
        }
        return null;
    }

    public static VehicleDto buildVehicle (ElectricVehicle electricVehicle) {
        return VehicleDto.builder()
                .id(electricVehicle.getId())
                .vin(electricVehicle.getVin())
                .registrationPlate(electricVehicle.getRegistrationPlate())
                .voltage(electricVehicle.getVoltage())
                .current(electricVehicle.getCurrent())
                .battery(electricVehicle.getBattery().name())
                .createdDate(Date.from(electricVehicle.getCreatedDate()))
                .updatedDate(Date.from(electricVehicle.getUpdatedDate()))
                .build();
    }

    public static VehicleDto buildVehicle (DieselVehicle dieselVehicle) {
        return VehicleDto.builder()
                .id(dieselVehicle.getId())
                .vin(dieselVehicle.getVin())
                .registrationPlate(dieselVehicle.getRegistrationPlate())
                .registrationPlate(dieselVehicle.getRegistrationPlate())
                .injectionPump(dieselVehicle.getInjectionPump().name())
                .createdDate(Date.from(dieselVehicle.getCreatedDate()))
                .updatedDate(Date.from(dieselVehicle.getUpdatedDate()))
                .build();
    }

    public static VehicleDto buildVehicle (GasolineVehicle gasolineVehicle) {
        return VehicleDto.builder()
                .id(gasolineVehicle.getId())
                .vin(gasolineVehicle.getVin())
                .registrationPlate(gasolineVehicle.getRegistrationPlate())
                .registrationPlate(gasolineVehicle.getRegistrationPlate())
                .fuels(List.of(StringUtils.commaDelimitedListToStringArray(gasolineVehicle.getFuelTypes())))
                .createdDate(Date.from(gasolineVehicle.getCreatedDate()))
                .updatedDate(Date.from(gasolineVehicle.getUpdatedDate()))
                .build();
    }

    public static VehicleDto buildVehicleFromRequest (VehicleRequest vehicleRequest) {
        return VehicleDto.builder()
                .vin(vehicleRequest.getVin())
                .registrationPlate(vehicleRequest.getRegistrationPlate())

                .fuels(vehicleRequest.getFuels())

                .battery(vehicleRequest.getBattery())
                .voltage(vehicleRequest.getVoltage())
                .current(vehicleRequest.getCurrent())

                .injectionPump(vehicleRequest.getInjectionPump())

                .build();
    }

    public static VehicleResponse buildVehicleResponse (VehicleDto vehicleDto) {
        if (vehicleDto.getFuels() != null && !vehicleDto.getFuels().isEmpty()) {
            return buildGasolineVehicleResponse(vehicleDto);
        }
        if (vehicleDto.getInjectionPump() != null) {
            return buildDieselVehicleResponse(vehicleDto);
        }
        if (vehicleDto.getBattery() != null) {
            return buildElectricVehicleResponse(vehicleDto);
        }
        return null;
    }

    private static VehicleResponse buildGasolineVehicleResponse (VehicleDto vehicleDto) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        GasolineVehicleResponse gasolineVehicleResponse = new GasolineVehicleResponse();
        gasolineVehicleResponse.setId(vehicleDto.getId());
        gasolineVehicleResponse.setVin(vehicleDto.getVin());
        gasolineVehicleResponse.setRegistrationPlate(vehicleDto.getRegistrationPlate());
        gasolineVehicleResponse.setCreatedDate(dateFormat.format(vehicleDto.getCreatedDate()));
        gasolineVehicleResponse.setUpdatedDate(dateFormat.format(vehicleDto.getUpdatedDate()));
        gasolineVehicleResponse.setFuelTypes(vehicleDto.getFuels());
        return gasolineVehicleResponse;
    }

    private static VehicleResponse buildDieselVehicleResponse (VehicleDto vehicleDto) {
        InjectionPump injectionPump = InjectionPump.valueOf(vehicleDto.getInjectionPump());
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        DieselVehicleResponse dieselVehicleResponse = new DieselVehicleResponse();
        dieselVehicleResponse.setId(vehicleDto.getId());
        dieselVehicleResponse.setVin(vehicleDto.getVin());
        dieselVehicleResponse.setRegistrationPlate(vehicleDto.getRegistrationPlate());
        dieselVehicleResponse.setCreatedDate(dateFormat.format(vehicleDto.getCreatedDate()));
        dieselVehicleResponse.setUpdatedDate(dateFormat.format(vehicleDto.getUpdatedDate()));
        dieselVehicleResponse.setInjectionPump(injectionPump.getValue());
        return dieselVehicleResponse;
    }

    private static VehicleResponse buildElectricVehicleResponse (VehicleDto vehicleDto) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        ElectricVehicleResponse electricVehicleResponse = new ElectricVehicleResponse();
        electricVehicleResponse.setId(vehicleDto.getId());
        electricVehicleResponse.setVin(vehicleDto.getVin());
        electricVehicleResponse.setRegistrationPlate(vehicleDto.getRegistrationPlate());
        electricVehicleResponse.setCreatedDate(dateFormat.format(vehicleDto.getCreatedDate()));
        electricVehicleResponse.setUpdatedDate(dateFormat.format(vehicleDto.getUpdatedDate()));
        electricVehicleResponse.setBattery(Battery.valueOf(vehicleDto.getBattery()).getValue());
        electricVehicleResponse.setCurrent(vehicleDto.getCurrent());
        electricVehicleResponse.setVoltage(vehicleDto.getVoltage());
        return electricVehicleResponse;
    }

    public static VehicleRegisterInformationResponse buildVehicleRegisterInformationResponse (VehicleDto vehicleDto) {
        VehicleRegisterInformationResponse vehicleRegisterInformationResponse = new VehicleRegisterInformationResponse();
        String registerInformation;
        if (vehicleDto.getBattery() != null) {
            vehicleRegisterInformationResponse.setCarType(CarType.ELECTRIC.getValue());
            registerInformation = String.format("%s %s %s %s", vehicleDto.getVin(), vehicleDto.getVoltage(), vehicleDto.getCurrent(), Battery.valueOf(vehicleDto.getBattery()).getValue());
            vehicleRegisterInformationResponse.setRegisterInformation(registerInformation);
            String reconversionData = String.format("%s %s", vehicleDto.getRegistrationPlate(), StringUtils.collectionToDelimitedString(List.of(FuelType.values()), " "));
            vehicleRegisterInformationResponse.setReconversionData(reconversionData);
        } else if (vehicleDto.getFuels() != null && !vehicleDto.getFuels().isEmpty()) {
            vehicleRegisterInformationResponse.setCarType(CarType.GASOLINE.getValue());
            registerInformation = String.format("%s %s", vehicleDto.getRegistrationPlate(), StringUtils.collectionToDelimitedString(vehicleDto.getFuels(), " "));
            vehicleRegisterInformationResponse.setRegisterInformation(registerInformation);
        } else if (vehicleDto.getInjectionPump() != null) {
            vehicleRegisterInformationResponse.setCarType(CarType.DIESEL.getValue());
            registerInformation = String.format("%s %s", vehicleDto.getRegistrationPlate(), vehicleDto.getInjectionPump());
            vehicleRegisterInformationResponse.setRegisterInformation(registerInformation);
        } else {
            return null;
        }
        return vehicleRegisterInformationResponse;
    }
}
