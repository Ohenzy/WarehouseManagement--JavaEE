package com.ohenzy.warehouse.models;

public class Unit {

    private int id;
    private String name;


    public Unit(String name) {
        this.name = name;
    }

    public Unit(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
