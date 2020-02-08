package com.gojek.parkingLot.print;

import java.util.Set;
import java.util.stream.Collectors;

public class PrintRegNoGivenColor<T> implements Print<T> {

    @Override
    public void print(T dataToBePrinted) {
        Set<String> listToBePrinted = (Set<String>) dataToBePrinted;
        if (listToBePrinted.size() > 0) {
            System.out.println(listToBePrinted.stream()
                    .collect(Collectors.joining(", ")));
        } else{
            System.out.println("No cars with given colors");
        }
    }
}
