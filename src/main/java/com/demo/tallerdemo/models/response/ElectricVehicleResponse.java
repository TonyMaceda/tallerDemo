package com.demo.tallerdemo.models.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ElectricVehicleResponse extends VehicleResponse {

    private String battery;
    private String voltage;
    private String current;
}
