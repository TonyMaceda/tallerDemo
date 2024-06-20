package com.demo.tallerdemo.models.pojo;

import com.demo.tallerdemo.models.enums.InjectionPump;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@DiscriminatorValue("DIESEL")
@Getter
@Setter
public class DieselVehicle extends Vehicle {

    @Enumerated(EnumType.STRING)
    @Column(name = "injection_pump")
    @Nonnull
    private InjectionPump injectionPump;

}
