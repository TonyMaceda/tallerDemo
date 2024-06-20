package com.demo.tallerdemo.models.pojo;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("GASOLINE")
@Getter
@Setter
public class GasolineVehicle extends Vehicle {

    @Column(name = "fuel_types")
    @Nonnull
    private String fuelTypes;

}
