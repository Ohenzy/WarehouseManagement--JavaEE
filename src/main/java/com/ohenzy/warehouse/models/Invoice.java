package com.ohenzy.warehouse.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Invoice {

    private int id;
    private final Date date;
    private final String type;
    private final Partner partner;
    private final List<InvoiceProduct> products = new ArrayList<>();

    public Invoice(int id, Date date, String type, Partner partner, List<InvoiceProduct> productList){
        this(date, type, partner, productList);
        this.id = id;
    }

    public Invoice(Date date, String type, Partner partner, List<InvoiceProduct> productList){
        this.date = date;
        this.type = type;
        this.partner = partner;
        products.addAll(productList);
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public Partner getPartner() {
        return partner;
    }

    public List<InvoiceProduct> getProducts() {
        return products;
    }
}
