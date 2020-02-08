package com.gojek.parkingLot.inputProcessor;

import com.gojek.parkingLot.constants.Commands;
import com.gojek.parkingLot.exceptions.ParkingException;
import com.gojek.parkingLot.models.VehicleFactory;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

public class InteractiveCmdLineProcessorAbstract extends AbstractInputProcessor {

    public InteractiveCmdLineProcessorAbstract() {
        super(new VehicleFactory());

    }
    public void process() throws IOException, ParkingException {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));

        //continuosly reading
        while (true) {
            String inputString = bufferRead.readLine();
            if(inputString.length() == 0){
                continue;
            }
            InputParam inputParam = splitInput(inputString);
            Optional<Commands> action = validator(inputParam);
            if(action.isPresent())
                executeCmd(inputParam, action.get());
        }
    }
}
