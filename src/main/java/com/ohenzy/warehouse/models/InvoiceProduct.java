package com.ohenzy.warehouse.models;

public class InvoiceProduct {

    private int id;
    private final String name;
    private final int quantity;
    private final String unit;
    private final int price;
    private final Warehouse warehouse;
    private final int invoiceId;



    public InvoiceProduct(String name, int quantity, String unit, int price, Warehouse warehouse, int invoiceId) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.price = price;
        this.warehouse = warehouse;
        this.invoiceId = invoiceId;
    }

    public InvoiceProduct(int id, String name, int quantity, String unit, int price, Warehouse warehouse, int invoiceId) {
        this(name,quantity,unit, price, warehouse, invoiceId);
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

    public int getPrice() {
        return price;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public int getInvoiceId(){
        return invoiceId;
    }



}
