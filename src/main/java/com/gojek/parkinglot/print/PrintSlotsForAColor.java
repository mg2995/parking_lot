package com.gojek.parkinglot.print;

import java.util.List;
import java.util.stream.Collectors;

public class PrintSlotsForAColor<T> implements Print<T> {

    @Override
    public void print(T dataToBePrinted) {
        List<Integer> listToBePrinted = (List<Integer>) dataToBePrinted;
        if (listToBePrinted.size() > 0) {
            System.out.println(listToBePrinted.stream()
                    .map(value -> value.toString())
                    .collect(Collectors.joining(", ")));
        } else{
            System.out.println("not found");
        }
    }
}
