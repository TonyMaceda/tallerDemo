package com.demo.tallerdemo.models.enums;

import lombok.Getter;

@Getter
public enum InjectionPump {

    LINEAR("Lineal"), ROTARY("Rotatoria");

    final String value;

    InjectionPump (String value) {
        this.value = value;
    }

}
