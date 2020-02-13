package com.ohenzy.warehouse.models;

public class Product {

    private int id;
    private String name;
    private int quantity;
    private int unit;


    public Product(int id, String name, int quantity, int unit) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getUnit() {
        return unit;
    }
}
