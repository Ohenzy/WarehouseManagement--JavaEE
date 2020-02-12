package com.ohenzy.warehouse.models;

public class Partner {

    private int id;
    private String nameOrganization;
    private String nameDirector;
    private String address;
    private String phone;
    private String email;
    private String INN;
    private String OGRN;

    public Partner(int id, String nameOrganization, String nameDirector, String address, String phone, String email, String INN, String OGRN) {
        this.id = id;
        this.nameOrganization = nameOrganization;
        this.nameDirector = nameDirector;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.INN = INN;
        this.OGRN = OGRN;
    }

    public Partner(String nameOrganization, String nameDirector, String address, String phone, String email, String INN, String OGRN) {
        this.nameOrganization = nameOrganization;
        this.nameDirector = nameDirector;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.INN = INN;
        this.OGRN = OGRN;
    }

    public int getId() {
        return id;
    }

    public String getNameOrganisation() {
        return nameOrganization;
    }

    public String getNameDirector() {
        return nameDirector;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getINN() {
        return INN;
    }

    public String getOGRN() {
        return OGRN;
    }
}
