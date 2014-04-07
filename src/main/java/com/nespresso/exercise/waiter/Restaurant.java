package com.nespresso.exercise.waiter;

import com.nespresso.exercise.waiter.order.Order;

public class Restaurant {
    private static final String SEPARATOR_MSG = ":";
    private final Waiter waiter;

    public Restaurant() {
        waiter = new Waiter();
    }

    public int initTable(int sizeOfTable) {
        return waiter.initTable(sizeOfTable);
    }

    public void customerSays(int tableId, String message) {
        String customerName = makeCustomerNameFromMessage(message);
        String plateName = makePlateNameFromMessage(message);
        waiter.customerAsked(tableId, customerName, plateName);
    }

    private String makePlateNameFromMessage(String message) {
        final int indexOfSeparator = message.indexOf(SEPARATOR_MSG);
        String plateName = message.substring(indexOfSeparator + 1, message.length()).trim();
        return plateName;
    }

    private String makeCustomerNameFromMessage(String message) {
        final int indexOfSeparator = message.indexOf(SEPARATOR_MSG);
        String customerName = message.substring(0, indexOfSeparator);
        return customerName;
    }

    public String createOrder(int tableId) {
        try {
            final Order orderCreated = waiter.createOrder(tableId);
            return orderCreated.print();
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

}
