package com.nespresso.exercise.waiter.table;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.nespresso.exercise.waiter.customer.Customer;
import com.nespresso.exercise.waiter.order.Order;
import com.nespresso.exercise.waiter.plate.Plate;

public class Table {

    private static final String SAME_PLATE = "Same";
    private static final String FOR = "for";
    private static final String MISSING = "MISSING";
    private final int sizeOfTable;
    private final LinkedHashMap<Customer, Plate> platesCustomer;

    private final Map<Plate, Integer> platesFor;

    public Table(int sizeOfTable) {
        this.sizeOfTable = sizeOfTable;
        this.platesCustomer = new LinkedHashMap<Customer, Plate>();
        platesFor = new LinkedHashMap<Plate, Integer>();
    }

    public void makePlat(String customerName, String plateName) {
        final Customer customer = new Customer(customerName);
        final Plate plate;
        if (isSamePlate(plateName)) {
            plate = createSamePlatFromLastPlate();
        } else {
            plate = new Plate(plateName);
            if (plate.isPlateFor()) {
                addToPlatesFor(plate, plateName);
            }
        }
        platesCustomer.put(customer, plate);
    }

    private void addToPlatesFor(final Plate plate, String plateName) {
        if (platesFor.containsKey(plate)) {
            final int valueNumber = platesFor.get(plate) - 1;
            if (valueNumber == 0) {
                platesFor.remove(plate);
            } else {
                platesFor.put(plate, valueNumber);
            }
        } else {
            Integer nomberOfPlate = plate.makeNumberPlateFor();
            platesFor.put(plate, nomberOfPlate - 1);
        }
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
        if (platesNumberIsSameOfCustomerNumber() && plateForIsCommanded()) {
            return new Order(platesOrder);
        } else if (platesCustomer.size() < sizeOfTable) {
            throw new RuntimeException(MISSING + " " + (sizeOfTable - platesCustomer.size()));
        } else if (!plateForIsCommanded()) {
            StringBuilder platForOut = constructMessageMessingPlat();
            throw new RuntimeException(platForOut.toString());
        } else {
            throw new RuntimeException("Number of plates is higher the number of customers!");
        }
    }

    private boolean platesNumberIsSameOfCustomerNumber() {
        return platesCustomer.size() == sizeOfTable;
    }

    private StringBuilder constructMessageMessingPlat() {
        StringBuilder platForOut = new StringBuilder();
        final Iterator<Plate> iteratorMissongPlatesFor = platesFor.keySet().iterator();
        if (iteratorMissongPlatesFor.hasNext()) {
            final Plate missongPlateFor = iteratorMissongPlatesFor.next();
            platForOut.append(makeMessingPlateFor(missongPlateFor));
        }
        while (iteratorMissongPlatesFor.hasNext()) {
            Plate plate = iteratorMissongPlatesFor.next();
            platForOut.append("\n");
            platForOut.append(makeMessingPlateFor(plate));
        }
        return platForOut;
    }

    private String makeMessingPlateFor(Plate plate) {
        return MISSING + " " + (platesFor.get(plate)) + " " + FOR + " " + plate.print();
    }

    private boolean plateForIsCommanded() {
        return platesFor.isEmpty();
    }
}
