package com.ohenzy.warehouse.models;

public class Product {

    private int id;
    private String name;
    private int quantity;
    private String unit;


    public Product(String name, int quantity, String unit) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }

    public Product(int id, String name, int quantity, String unit) {
        this(name,quantity,unit);
        this.id = id;
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

    public String getUnit() {
        return unit;
    }
}
