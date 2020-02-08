package com.gojek.parkinglot;

import com.gojek.parkinglot.inputProcessor.AbstractInputProcessor;
import com.gojek.parkinglot.inputProcessor.FileParser;
import com.gojek.parkinglot.inputProcessor.InteractiveCmdLineProcessorAbstract;

public class Manager {
    public static void main(String[] args) {
        AbstractInputProcessor processor;
        if (args.length >= 1) {
            processor = new FileParser(args[0]);
        } else {
            processor = new InteractiveCmdLineProcessorAbstract();
        }
        try {
            processor.process();
        } catch (Exception e){
            System.out.println("Exception has occurred, please try again");
        }
    }
}
