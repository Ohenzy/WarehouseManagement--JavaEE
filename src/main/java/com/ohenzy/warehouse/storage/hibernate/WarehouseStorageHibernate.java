package com.ohenzy.warehouse.storage.hibernate;

import com.ohenzy.warehouse.models.Warehouse;
import com.ohenzy.warehouse.storage.Storage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class WarehouseStorageHibernate implements Storage<Warehouse> {

    public static void main(String[]args){
        WarehouseStorageHibernate warehouses = new WarehouseStorageHibernate();
//        warehouses.save(new Warehouse("Оптовый", "Очакого 687", "8972346854"));
//        warehouses.save(new Warehouse("Овощьной", "Очакого 123", "89723545678546854"));
//        warehouses.save(new Warehouse("Быт техника", "Хакурате 45", "23423452356"));



//        for (Warehouse warehouse : warehouses.findAll()){
//            System.out.println(" | " + warehouse.getId() + " | " + warehouse.getName() + " | " + warehouse.getAddress() + " | " + warehouse.getPhone() + " | ");
//        }
    }


    private final SessionFactory sessionFactory;

    public WarehouseStorageHibernate(){
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    @Override
    public void save(final Warehouse warehouse) {
        final Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            session.save(warehouse);
        } finally {
            session.getTransaction().commit();
            session.close();
        }

    }

    @Override
    public Warehouse findById(int id) {
        return null;
    }

    @Override
    public List<Warehouse> findAll() {
        return null;
    }

    @Override
    public boolean existsById(int id) {
        return false;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void deleteAll() {

    }
}
