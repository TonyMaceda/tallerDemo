package com.demo.tallerdemo.models.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GasolineVehicleResponse extends VehicleResponse {

    private List<String> fuelTypes;
}
