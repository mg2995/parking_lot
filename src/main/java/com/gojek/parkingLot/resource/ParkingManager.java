package com.gojek.parkingLot.resource;

import com.gojek.parkingLot.exceptions.ParkingException;
import com.gojek.parkingLot.models.Ticket;
import com.gojek.parkingLot.models.Vehicle;
import com.gojek.parkingLot.responses.ParkingStatusResponse;

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
