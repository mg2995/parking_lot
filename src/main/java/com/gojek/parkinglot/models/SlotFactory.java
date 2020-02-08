package com.gojek.parkinglot.models;

public class SlotFactory {
    Slot provideSlot(int number, SlotType type){
        if(SlotType.Medium.equals(type)){
            return new MediumSlot(number);
        } else{
            throw new RuntimeException("Illegal SlotType. Please check");
        }
    }
}
