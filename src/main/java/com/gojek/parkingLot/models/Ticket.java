package com.gojek.parkingLot.models;

import java.util.Date;

public class Ticket {
    private final int slotNumber;
    private  final long entryTime;

    //can be avoided but just for extra validation
    private final String registrationNo;

    Ticket(int slot, String registrationNo){
        Date date = new Date();
        this.entryTime = date.getTime();
        this.slotNumber= slot;
        this.registrationNo = registrationNo;
    }

}
