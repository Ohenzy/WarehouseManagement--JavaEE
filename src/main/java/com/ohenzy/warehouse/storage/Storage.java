package com.ohenzy.warehouse.storage;

import java.util.List;

public interface Storage<T> {
    void save(T entity);
    T findById(int id);
    List<T> findAll();
    boolean existsById(int id);
    void deleteById(int id);
    void deleteAll();
}
