package com.gojek.parkingLot.exceptions;

public class ParkingException extends Exception{
    public ParkingException(){
        super();
    }
    public ParkingException(String s){
        super(s);
    }
}
