package com.gojek.parkingLot.inputProcessor;

import com.gojek.parkingLot.constants.Commands;
import com.gojek.parkingLot.exceptions.ParkingException;

import java.util.Optional;

public interface Processor {

    void executeCmd(InputParam parameters, Commands cmd) throws ParkingException;

    Optional<Commands> validator(InputParam parameters) throws ParkingException;

    InputParam splitInput(String input) throws Exception;
}

