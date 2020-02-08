package com.gojek.parkinglot.models;

public abstract class Vehicle {
    private String color;
    private String registrationNo;
    private VehicleType type;
    private static final VehicleType defaultType = VehicleType.CAR;

    Vehicle(String color, String registrationNo) {
        this.color = color;
        this.registrationNo = registrationNo;
        this.type = defaultType;
    }

    Vehicle(String color, String registrationNo, VehicleType type) {
        //Additional validation on color & registrationNo can be added here
        this.color = color;
        this.registrationNo = registrationNo;
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public VehicleType getVehicleType(){
        return type;
    }
}
