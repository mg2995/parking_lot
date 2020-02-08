package com.gojek.parkinglot.inputProcessor;

import com.gojek.parkinglot.constants.Commands;
import com.gojek.parkinglot.exceptions.ParkingException;
import com.gojek.parkinglot.models.VehicleFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

public class FileParser extends AbstractInputProcessor {

    String file;

    public FileParser(String file) {
        super(new VehicleFactory());
        this.file = file;
    }

    public void process() throws IOException, ParkingException {
        File inputFile = new File(file);
        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        String line;

        while ((line = br.readLine()) != null) {
            InputParam inputParam = splitInput(line);
            Optional<Commands> action = validator(inputParam);
            executeCmd(inputParam, action.get());
        }
    }
}

