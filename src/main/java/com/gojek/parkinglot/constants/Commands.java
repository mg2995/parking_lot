package com.gojek.parkinglot.constants;


import java.util.Optional;

public enum Commands {

    CREATE_PARKING_LOT("create_parking_lot"),
    PARK("park"),
    LEAVE("leave"),
    REGISTRATION_GIVEN_COLOR("registration_numbers_for_cars_with_colour"),
    STATUS("status"),
    SLOT_GIVEN_REGISTRATION("slot_number_for_registration_number"),
    SLOTS_GIVEN_COLOR("slot_numbers_for_cars_with_colour"),
    EXIT("exit");

    Commands(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
    public final String value;

    public static Optional<Commands> findByCmd(String cmd){
        for(Commands c : values()){
            if( c.getValue().equals(cmd)){
                return Optional.of(c);
            }
        }
        return Optional.empty();
    }
}
