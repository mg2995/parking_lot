package com.gojek.parkinglot.print;

public class PrintContext <T> {
    private final Print strategy;

    public PrintContext(Print strategy) {
        this.strategy = strategy;
    }

    public void print(T input) {
        strategy.print(input);
    }
}
