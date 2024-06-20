package com.demo.tallerdemo.models.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DieselVehicleResponse extends VehicleResponse {

    private String injectionPump;
}
