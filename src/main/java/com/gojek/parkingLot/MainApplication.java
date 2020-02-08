package com.gojek.parkingLot;

import com.gojek.parkingLot.inputProcessor.AbstractInputProcessor;
import com.gojek.parkingLot.inputProcessor.FileParser;
import com.gojek.parkingLot.inputProcessor.InteractiveCmdLineProcessorAbstract;

public class MainApplication {
    public static void main(String[] args) {
        System.out.println("Hello there! So please go ahead and evaluate me");
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
