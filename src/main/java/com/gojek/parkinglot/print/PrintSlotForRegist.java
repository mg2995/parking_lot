package com.gojek.parkinglot.print;

public class PrintSlotForRegist<T> implements Print<T> {

    @Override
    public void print(T dataToBePrinted) {
        Integer slot = (Integer) dataToBePrinted;
        if (slot == -1) {
            System.out.println("Not found");
        } else {
            System.out.println(slot);
        }
    }

}
