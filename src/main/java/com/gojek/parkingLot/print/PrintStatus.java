package com.gojek.parkingLot.print;


import com.gojek.parkingLot.responses.ParkingStatusResponse;

import java.util.List;

public class PrintStatus<T> implements Print<T> {

    @Override
    public void print(T dataToBePrinted) {
        List<ParkingStatusResponse> s1 = (List<ParkingStatusResponse>) dataToBePrinted;
        if(s1.size() ==0 || s1 == null){
            System.out.println("There is no vehicle parked");
        }
        System.out.printf("%-11s %-18s %s", "Slot No.",  "Registration No",   "Colour");
        System.out.println();
        for (Object plr1: s1
                ) {
            ParkingStatusResponse plr = (ParkingStatusResponse) plr1;
            System.out.printf("%-11d %-18s %s", plr.getSpotNumber(), plr.getRegNumber(), plr.getColor());
            System.out.println();
        }
    }
}
