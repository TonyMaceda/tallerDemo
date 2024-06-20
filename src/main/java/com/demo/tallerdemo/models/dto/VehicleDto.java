package com.demo.tallerdemo.models.dto;

import com.demo.tallerdemo.models.enums.Battery;
import com.demo.tallerdemo.models.enums.InjectionPump;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
public class VehicleDto {

    private Long id;

    private Date createdDate;

    private Date updatedDate;

    private String registrationPlate;

    private String vin;

    private List<String> fuels;

    private String injectionPump;

    private String battery;

    private String voltage;

    private String current;

}
