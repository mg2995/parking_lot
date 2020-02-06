package com.gojek.parkingLot.resource;

import com.gojek.parkingLot.exceptions.ParkingException;
import com.gojek.parkingLot.models.Ticket;
import com.gojek.parkingLot.models.Vehicle;
import com.gojek.parkingLot.responses.ParkingStatusResponse;

import java.util.List;

public interface ParkingManager {
    int park(Vehicle vehicle) throws ParkingException;
    int leave(int slot);
    int getNearestAvailableSlot();
    List<Integer> getAllAvailableSlots();
    List<Integer> getAllOccupiedSlots();
    List<String> getRegistrationNumber(String color) throws ParkingException;
    Integer getCarSlot(String registrationNo) throws ParkingException;
    List<Integer> getCarSlotsWithColor(String color) throws ParkingException;
    List<ParkingStatusResponse> getStatus() throws ParkingException;

}
