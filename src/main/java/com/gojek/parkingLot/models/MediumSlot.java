package com.gojek.parkingLot.models;

public class MediumSlot extends Slot{
    private static final SlotType type = SlotType.Medium;
    MediumSlot(int number){
        super(number, type);
    }
}
