package com.demo.tallerdemo.models.enums;

public enum CarType {

    DIESEL("DIESEL"), GASOLINE("GASOLINE"), ELECTRIC("ELECTRIC");

    final String value;

    CarType (String value) {
        this.value = value;
    }

    public String getValue () {
        return this.value;
    }
}
