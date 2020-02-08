package com.gojek.parkinglot.models;

public class VehicleFactory {
    public Vehicle buildVehicle(String registrationNo, String color, VehicleType vehicleType) {

        //Can be replaced with switch if more arguments.
        if(vehicleType.equals(VehicleType.CAR)){
            return new Car(color, registrationNo);
        } else{
            throw  new RuntimeException("Illegal type for vehicle");
        }

    }
}
