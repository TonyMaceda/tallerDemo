package com.demo.tallerdemo.models.pojo;

import com.demo.tallerdemo.models.enums.Battery;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("ELECTRIC")
@Getter
@Setter
public class ElectricVehicle extends Vehicle {

    @Enumerated(EnumType.STRING)
    @Nonnull
    private Battery battery;

    @Column
    @Nonnull
    private String voltage;

    @Column
    @Nonnull
    private String current;

}
