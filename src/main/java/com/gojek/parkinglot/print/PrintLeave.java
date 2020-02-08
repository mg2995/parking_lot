package com.gojek.parkinglot.print;

public class PrintLeave<T> implements Print<T> {

    @Override
    public void print(T dataToBePrinted) {
        int spotNumber = (Integer) dataToBePrinted;
        if (spotNumber == -1) {
            System.out.println("Not found | Invalid Input, please check");
        } else {
            System.out.println("Slot number " + spotNumber + " is free");
        }
    }
}
