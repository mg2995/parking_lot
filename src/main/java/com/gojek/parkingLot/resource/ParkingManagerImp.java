package com.gojek.parkingLot.resource;

import com.gojek.parkingLot.exceptions.ErrorCodes;
import com.gojek.parkingLot.exceptions.ParkingException;
import com.gojek.parkingLot.models.MediumSlot;
import com.gojek.parkingLot.models.Slot;
import com.gojek.parkingLot.models.Vehicle;
import com.gojek.parkingLot.responses.ParkingStatusResponse;

import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

public class ParkingManagerImp implements ParkingManager {
    //COLOR is based on lower case

    public static final String CAR_DOESN_T_EXIST = "Car doesn't exist";
    private static final int defaultSlots = 100;
    static final String PARKING_ALREADY_EMPTY = "Parking already empty";
    static final String ENTER_VALID_SLOT_NUMBER = "Enter Valid slot number";

    //Check for -1 everytime as indexing of slot is from 1
    final List<Slot> slots;
    private Queue<Integer> availableSlots;
    //Map<Color, Treemap<Registration, slotno>>
    Map<String, TreeMap<String, Integer>> colorMapping;
    final Integer totalSlots;
    ReentrantReadWriteLock reentrantLock;

    private ParkingManagerImp(int totalSlots,
                              Queue<Integer> availableSlots, List<Slot> slots) {
        this.availableSlots = availableSlots;
        this.slots = slots;
        this.reentrantLock = new ReentrantReadWriteLock();
        this.totalSlots = (totalSlots == 0) ? defaultSlots : totalSlots;
        this.colorMapping = new HashMap<>();
    }

    public static ParkingManagerImp getParkingInstance(int count) {
        //initialise all the slots
        List<Slot> slots = new ArrayList<>();
        //init availableSlots
        Queue<Integer> availableSlots = new PriorityQueue<>();
        for (int ind = 0; ind < count; ind++) {
            Slot currSlot = new MediumSlot(ind + 1);
            slots.add(currSlot);
            availableSlots.add(ind + 1);
        }
        return new ParkingManagerImp(count, availableSlots, slots);
    }

    @Override
    public int park(Vehicle vehicle) throws ParkingException {
        int nextAvailableSlot = -1;


        if (!(availableSlots.isEmpty() || (colorMapping.containsKey(vehicle.getColor().toLowerCase())
                && colorMapping.get(vehicle.getColor().toLowerCase()).containsKey(vehicle.getRegistrationNo())))) {
            reentrantLock.writeLock().lock();
            try {
                nextAvailableSlot = availableSlots.poll();
                Slot availSlot = slots.get(nextAvailableSlot - 1);
                availSlot.setVehicle(vehicle);

                //setting up the color Map
                String color = vehicle.getColor().toLowerCase();
                if (colorMapping.containsKey(color)) {
                    TreeMap map = colorMapping.get(color);
                    map.put(vehicle.getRegistrationNo(), nextAvailableSlot);
                } else {
                    TreeMap map = new TreeMap<>();
                    map.put(vehicle.getRegistrationNo(), nextAvailableSlot);
                    colorMapping.put(color, map);
                }
            } catch (Exception e) {
                if (nextAvailableSlot != -1) {
                    slots.get(nextAvailableSlot - 1).setVehicle(null);
                    availableSlots.add(nextAvailableSlot);

                }
                throw new ParkingException(ErrorCodes.UNABLE_TO_PARK.getMsg(), e);
            } finally {
                reentrantLock.writeLock().unlock();
            }
        } else if ((colorMapping.containsKey(vehicle.getColor().toLowerCase())
                && colorMapping.get(vehicle.getColor().toLowerCase()).containsKey(vehicle.getRegistrationNo()))) {
            nextAvailableSlot = -2;
        }
        return nextAvailableSlot;

    }

    @Override
    public boolean leave(int slotNumber) throws ParkingException {
        if (slotNumber > totalSlots || slotNumber <= 0) {
            //System.out.println(ENTER_VALID_SLOT_NUMBER);
            return false;
        }
        if (!slots.get(slotNumber - 1).isNotEmpty()) {
            //System.out.println(PARKING_ALREADY_EMPTY);
            return false;
        }
        reentrantLock.writeLock().lock();
        try {
            Vehicle vehicle = slots.get(slotNumber - 1).getVehicle();
            TreeMap map = colorMapping.get(vehicle.getColor().toLowerCase());
            map.remove(vehicle.getRegistrationNo());
            if (map.isEmpty()) {
                colorMapping.remove(vehicle.getColor().toLowerCase());
            }
            slots.get(slotNumber - 1).setVehicle(null);
            availableSlots.add(slotNumber);
        } catch (Exception e) {
            throw new ParkingException(ErrorCodes.UNABLE_TO_LEAVE_PARK.getMsg(), e);
        } finally {
            reentrantLock.writeLock().unlock();
        }
        return true;
    }

    @Override
    public int getNearestAvailableSlot() {
        return availableSlots.isEmpty() ? -1 : availableSlots.peek();
    }

    @Override
    public List<ParkingStatusResponse> getStatus() throws ParkingException {
        //Can be optimised by searching over hashmap and then sorting on the basis of slotNumber
        reentrantLock.readLock().lock();
        List<ParkingStatusResponse> status;
        try {
            status = slots.stream().filter((Slot::isNotEmpty)).map(slot -> {
                return new ParkingStatusResponse(slot.getNumber(),
                        slot.getVehicle().getRegistrationNo(), slot.getVehicle().getColor());
            }).collect(Collectors.toList());
        } catch (Exception e) {
            throw new ParkingException(ErrorCodes.GENERIC.getMsg(), e);
        } finally {
            reentrantLock.readLock().unlock();
        }

        return status;
    }

    public Set<String> getRegistrationNumbers(String color) throws ParkingException {
        Set<String> registList = new HashSet<>();
        reentrantLock.readLock().lock();
        try {
            if (colorMapping.containsKey(color.toLowerCase())) {
                registList = colorMapping.get(color.toLowerCase()).keySet();
            }

        } catch (Exception e) {
            throw new ParkingException(ErrorCodes.GENERIC.getMsg(), e);
        } finally {
            reentrantLock.readLock().unlock();
        }
        return registList;
    }

    public Integer getCarSlot(String registrationNo) throws ParkingException {
        int slotNumber = -1;
        reentrantLock.readLock().lock();
        try {
            Optional<Integer> pos = colorMapping.entrySet().stream().filter(entry -> entry.getValue().containsKey(registrationNo)).
                    map(entry -> entry.getValue().get(registrationNo)).findAny();
            if (pos.isPresent()) {
                slotNumber = pos.get();
            }
        } catch (Exception e) {
            throw new ParkingException(ErrorCodes.GENERIC.getMsg(), e);
        } finally {
            reentrantLock.readLock().unlock();
        }
        return slotNumber;
    }

    public List<Integer> getSlotsWithColor(String color) throws ParkingException {
        List<Integer> positions;
        reentrantLock.readLock().lock();
        try {
            if (colorMapping.containsKey(color.toLowerCase())) {
                positions = colorMapping.get(color.toLowerCase()).values().stream().collect(Collectors.toList());
            } else {
                positions = new ArrayList<>();
            }

        } catch (Exception e) {
            throw new ParkingException(ErrorCodes.GENERIC.getMsg(), e);
        } finally {
            reentrantLock.readLock().unlock();
        }
        return positions;
    }

}
