package com.nespresso.exercise.waiter.order;

import java.util.Iterator;
import java.util.List;

import com.nespresso.exercise.waiter.plate.Plate;

public class Order {

    private List<Plate> plates;

    public Order(List<Plate> plates) {
        this.plates = plates;
    }

    public String print() {
        final Iterator<Plate> iteratorPlat = plates.iterator();
        final Plate firstPlate = iteratorPlat.next();
        StringBuilder out = new StringBuilder(firstPlate.print());
        while (iteratorPlat.hasNext()) {
            out.append(", ");
            out.append(iteratorPlat.next().print());
        }
        return out.toString();
    }

}
