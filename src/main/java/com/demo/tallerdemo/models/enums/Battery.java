package com.demo.tallerdemo.models.enums;


import lombok.Getter;

@Getter
public enum Battery {

    GEL("Gel"), LITHIUM("Litio");

    final String value;

    Battery (String value) {
        this.value = value;
    }
}
