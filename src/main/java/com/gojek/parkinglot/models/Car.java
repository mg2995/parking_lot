package com.gojek.parkinglot.models;

public class Car extends Vehicle{
    Car(String color, String registrationNo){
        super(color, registrationNo, VehicleType.CAR);
    }
}
