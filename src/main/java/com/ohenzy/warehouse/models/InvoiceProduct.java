package com.ohenzy.warehouse.models;

public class InvoiceProduct {

    private int id;
    private int invoiceId;
    private final String name;
    private final int quantity;
    private final String unit;
    private final int price;
    private final Warehouse warehouse;



    public InvoiceProduct(String name, int quantity, String unit, int price, Warehouse warehouse) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.price = price;
        this.warehouse = warehouse;
    }

    public InvoiceProduct(int id, int invoiceId, String name, int quantity, String unit, int price, Warehouse warehouse ) {
        this(name,quantity,unit, price, warehouse);
        this.invoiceId = invoiceId;
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
