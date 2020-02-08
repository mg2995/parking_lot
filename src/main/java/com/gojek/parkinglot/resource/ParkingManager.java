package com.gojek.parkinglot.resource;

import com.gojek.parkinglot.exceptions.ParkingException;
import com.gojek.parkinglot.models.Vehicle;
import com.gojek.parkinglot.responses.ParkingStatusResponse;

import java.util.List;
import java.util.Set;

public interface ParkingManager {
    int park(Vehicle vehicle) throws ParkingException;
    boolean leave(int slot) throws ParkingException;
    int getNearestAvailableSlot();
    List<ParkingStatusResponse> getStatus() throws ParkingException;
    Set<String> getRegistrationNumbers(String color) throws ParkingException;
    Integer getCarSlot(String registrationNo) throws ParkingException;
    List<Integer> getSlotsWithColor(String color) throws ParkingException;

}
