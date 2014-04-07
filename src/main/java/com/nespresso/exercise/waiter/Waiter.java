package com.nespresso.exercise.waiter;

import java.util.HashMap;
import java.util.Map;

import com.nespresso.exercise.waiter.order.Order;
import com.nespresso.exercise.waiter.table.Table;

public class Waiter {

    private final Map<Integer, Table> tablesWaiter;

    public Waiter() {
        tablesWaiter = new HashMap<Integer, Table>();
    }

    public int initTable(int sizeOfTable) {
        Table table = prepartTableForThisNumberOfCustomer(sizeOfTable);
        final int idTable = createIdForThiseTable(table);
        tablesWaiter.put(idTable, table);
        return idTable;
    }

    private int createIdForThiseTable(Table table) {
        return tablesWaiter.keySet().size() + 1;
    }

    private Table prepartTableForThisNumberOfCustomer(int sizeOfTable) {
        return new Table(sizeOfTable);
    }

    public void customerAsked(int tableId, String customerName, String plateName) {
        Table table = verifyAndReturnTableFor(tableId);
        table.makePlat(customerName, plateName);
    }

    private Table verifyAndReturnTableFor(int tableId) {
        if (!isExisteTableId(tableId)) {
            throw new RuntimeException("Table not found!");
        }
        return tablesWaiter.get(tableId);
    }

    private boolean isExisteTableId(int tableId) {
        return tablesWaiter.containsKey(tableId);
    }

    public Order createOrder(int tableId) {
        Table table = verifyAndReturnTableFor(tableId);
        return table.constructOrder();
    }

}
