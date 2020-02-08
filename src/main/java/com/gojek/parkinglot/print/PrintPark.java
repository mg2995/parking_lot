package com.gojek.parkinglot.print;

public class PrintPark<T> implements Print<T> {

    @Override
    public void print(T dataToBePrinted) {
        Integer spotNumber = (Integer) dataToBePrinted;
        if (spotNumber == -1) {
            System.out.println("Sorry, parking lot is full");
        } else if(spotNumber == -2){
            System.out.println("Car is already present");
        }
        else {
            System.out.println("Allocated slot number: " + spotNumber);
        }
    }
}
