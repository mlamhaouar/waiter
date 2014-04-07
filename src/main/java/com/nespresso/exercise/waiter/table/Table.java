package com.nespresso.exercise.waiter.table;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.nespresso.exercise.waiter.customer.Customer;
import com.nespresso.exercise.waiter.order.Order;
import com.nespresso.exercise.waiter.plate.Plate;

public class Table {

    private static final String SAME_PLATE = "Same";
    private static final String PLATEFORTOW = "Fish for 2";
    private static final String MISSING = "MISSING";
    private final int sizeOfTable;
    private final LinkedHashMap<Customer, Plate> platesCustomer;

    private int numberPlateForTow;

    public Table(int sizeOfTable) {
        this.sizeOfTable = sizeOfTable;
        this.platesCustomer = new LinkedHashMap<Customer, Plate>();
    }

    public void makePlat(String customerName, String plateName) {
        final Customer customer = new Customer(customerName);
        final Plate plate;
        if (isSamePlate(plateName)) {
            plate = createSamePlatFromLastPlate();
        } else {
            plate = new Plate(plateName);
            if (isForTowPlat(plateName)) {
                numberPlateForTow++;
            }
        }
        platesCustomer.put(customer, plate);
    }

    private boolean isForTowPlat(String plateName) {
        return PLATEFORTOW.equals(plateName);
    }

    private boolean isSamePlate(String plateName) {
        return SAME_PLATE.equals(plateName);
    }

    private Plate createSamePlatFromLastPlate() {
        Plate lastPlate = platesCustomer.entrySet().iterator().next().getValue();
        return lastPlate;
    }

    public Order constructOrder() {
        List<Plate> platesOrder = new ArrayList<Plate>();
        for (Customer customer : platesCustomer.keySet()) {
            platesOrder.add(platesCustomer.get(customer));
        }
        if (platesCustomer.size() == sizeOfTable && numberPlateForTow % 2 == 0) {
            return new Order(platesOrder);
        } else if (platesCustomer.size() < sizeOfTable) {
            throw new RuntimeException(MISSING + " " + (sizeOfTable - platesCustomer.size()));
        } else if (numberPlateForTow % 2 != 0) {
            throw new RuntimeException(MISSING + " " + (numberPlateForTow % 2 + " for " + PLATEFORTOW));
        }
        return null;
    }
}
