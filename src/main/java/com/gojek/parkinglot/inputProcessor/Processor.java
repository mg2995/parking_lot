package com.gojek.parkinglot.inputProcessor;

import com.gojek.parkinglot.constants.Commands;
import com.gojek.parkinglot.exceptions.ParkingException;

import java.util.Optional;

public interface Processor {

    void executeCmd(InputParam parameters, Commands cmd) throws ParkingException;

    Optional<Commands> validator(InputParam parameters) throws ParkingException;

    InputParam splitInput(String input) throws Exception;
}

