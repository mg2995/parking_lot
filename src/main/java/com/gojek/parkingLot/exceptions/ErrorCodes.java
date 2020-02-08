package com.gojek.parkingLot.exceptions;

public enum  ErrorCodes {
    PARKING_LOT_NOT_EXIST("parking lot does not exist"),
    UNABLE_TO_PARK("Sorry, having troubles while parking the car, Please try again"),
    UNABLE_TO_LEAVE_PARK("Something went wrong, please show ticket again"),
    PARKING_LOT_ALREADY_PRESENT("Parking lot is already present"),
    NO_COMMAND("no Command given"),
    INVALID_COMMAND("invalid command entered"),
    GENERIC("Please try again");


    String msg;
    ErrorCodes(String msg){
        this.msg = msg;
    }
    public String getMsg(){
        return msg;
    }
}
