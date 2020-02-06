package com.gojek.parkingLot.models;

public abstract class Slot {
    private final SlotType slotType; //Default value
    private final int number;
    private Vehicle vehicle = null;
    private boolean isEmpty = true;
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

    Vehicle getVehicle(){
        return this.vehicle;
    }
    boolean isEmpty(){
        return  isEmpty;
    }
    int getCharges(){
        return charges;
    }
    int getNumber(){
        return number;
    }

    void setVehicle(Vehicle vehicle){

        this.vehicle = vehicle;
        isEmpty = (vehicle == null);
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
