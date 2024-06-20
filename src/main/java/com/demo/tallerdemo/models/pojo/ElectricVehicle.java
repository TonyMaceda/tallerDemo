package com.demo.tallerdemo.models.pojo;

import com.demo.tallerdemo.models.enums.Battery;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("ELECTRIC")
@Getter
@Setter
public class ElectricVehicle extends Vehicle {

    @Enumerated(EnumType.STRING)
    private Battery battery;

    @Column
    private String voltage;

    @Column
    private String current;

}
