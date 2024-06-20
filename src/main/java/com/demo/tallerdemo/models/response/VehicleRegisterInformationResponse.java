package com.demo.tallerdemo.models.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VehicleRegisterInformationResponse {

    private String carType;
    private String registerInformation;
    private String reconversionData;
}
