package com.ohenzy.warehouse.models;




public class Warehouse {

    private int id;
    private String name;
    private String address;
    private String phone;

    public Warehouse(int id,String name, String address, String phone){
        this(name,address,phone);
        this.id = id;
    }

    public Warehouse(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
}
