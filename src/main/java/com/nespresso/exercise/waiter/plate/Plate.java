package com.nespresso.exercise.waiter.plate;

public class Plate {

    private String name;

    public Plate(String name) {
        this.name = name;
    }

    public String print() {
        return this.name;
    }

    public Plate createSamePlat() {
        return new Plate(name);
    }

}
