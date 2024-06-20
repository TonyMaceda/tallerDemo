package com.demo.tallerdemo.models.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleResponse {

    private Long id;
    private String vin;
    private String registrationPlate;
    private String createdDate;
    private String updatedDate;

}
