package com.gojek.parkinglot.exceptions;

public class ParkingException extends Exception{
    public ParkingException(){
        super();
    }
    public ParkingException(String s){
        super(s);
    }
    public ParkingException(String message, Throwable cause) {
        super(message, cause);
    }
}
