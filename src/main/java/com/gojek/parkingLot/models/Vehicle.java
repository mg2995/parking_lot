package com.gojek.parkingLot.models;

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

    String getColor() {
        return color;
    }

    String getRegistrationNo() {
        return registrationNo;
    }

    VehicleType getVehicleType(){
        return type;
    }
}
