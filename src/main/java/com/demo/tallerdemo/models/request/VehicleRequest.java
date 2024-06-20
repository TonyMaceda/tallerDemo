package com.demo.tallerdemo.models.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VehicleRequest {

    private String vin;
    private String registrationPlate;
    private String battery;
    private String voltage;
    private String current;
    private List<String> fuels;
    private String injectionPump;
    private String carType;
}
