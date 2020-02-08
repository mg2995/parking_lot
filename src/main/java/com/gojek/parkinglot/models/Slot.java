package com.gojek.parkinglot.models;

public abstract class Slot {
    private final SlotType slotType; //Default value
    private final int number;
    private Vehicle vehicle = null;
    private int charges = 10; //Default charge per hour

    private final static SlotType defaultSlotType = SlotType.Medium;

    Slot(int number){
        this.number = number;
        this.slotType = defaultSlotType;
    }

    Slot(int number, SlotType slotType){
        this.number = number;
        this.slotType = slotType;
    }


    public boolean isNotEmpty() {
        return vehicle != null;
    }

    int getCharges(){
        return charges;
    }
    public int getNumber(){
        return number;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    void setCharges(int charges){
        this.charges = charges;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Slot anotherSlot = (Slot) o;
        return number == anotherSlot.number;
    }

    @Override
    public int hashCode() {
        //Objects.hash wasn't working and anyways this will suffice
        return number%310;
    }

}
