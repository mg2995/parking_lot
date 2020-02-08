package com.gojek.parkinglot.responses;

public class ParkingStatusResponse {
    private int spotNumber;
    private String regNumber;
    private String color;

    public ParkingStatusResponse(int spotNumber, String regNumber, String color) {
        this.spotNumber = spotNumber;
        this.regNumber = regNumber;
        this.color = color;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public String getColor() {
        return color;
    }
}
