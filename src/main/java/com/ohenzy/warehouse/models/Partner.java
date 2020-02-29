package com.ohenzy.warehouse.models;

public class Partner {

    private int id;
    private final String nameOrganization;
    private final String nameDirector;
    private final String address;
    private final String phone;
    private final String email;
    private final String INN;
    private final String OGRN;

    public Partner(int id, String nameOrganization, String nameDirector, String address, String phone, String email, String INN, String OGRN) {
        this(nameOrganization, nameDirector, address, phone, email, INN, OGRN);
        this.id = id;
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
