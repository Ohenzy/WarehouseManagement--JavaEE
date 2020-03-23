package com.ohenzy.warehouse.storage.jdbc;

import com.ohenzy.warehouse.models.Warehouse;
import com.ohenzy.warehouse.storage.Storage;
import com.ohenzy.warehouse.storage.util.ConnectorJDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WarehouseStorageJDBC implements Storage<Warehouse> {

    private final ConnectorJDBC connector = ConnectorJDBC.getInstance();

    public WarehouseStorageJDBC(){
        this.createTable();
    }

    private void createTable() {
        try {
            if( !tableExists() )
                connector.getConnection().createStatement().executeUpdate("create table warehouses ( warehouse_id int not null auto_increment primary key, name varchar (50), address varchar (50), phone varchar (50));");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean tableExists() throws SQLException{
        boolean tableExists = false;
        ResultSet result = connector.getConnection().createStatement().executeQuery("CHECK TABLE warehouses");
        while (result.next())
            if (result.getString("Msg_text").equals("OK"))
                tableExists = true;

        return tableExists;
    }

    public void deleteAll() {
        try {
            if(this.tableExists())
                connector.getConnection().createStatement().executeUpdate("drop table warehouses");

            this.createTable();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void save(Warehouse warehouse) {
        if(existsById(warehouse.getId())){
            try(PreparedStatement statement = connector.getConnection().prepareStatement("insert into warehouses (name, address, phone) values (?,?,?)")){
                statement.setString(1,warehouse.getName());
                statement.setString(2,warehouse.getAddress());
                statement.setString(3,warehouse.getPhone());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try(PreparedStatement statement = connector.getConnection().prepareStatement("update warehouses set name = ?, address = ?, phone = ? where warehouse_id = ?")){
                statement.setString(1, warehouse.getName());
                statement.setString(2, warehouse.getAddress());
                statement.setString(3, warehouse.getPhone());
                statement.setInt(4, warehouse.getId());
                statement.executeUpdate();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteById(int id) {
        try {
            connector.getConnection().createStatement().executeQuery("delete from warehouses where warehouse_id=" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean existsById(int id) {
        try {
            connector.getConnection().createStatement().executeUpdate("select * from warehouses where warehouse_id=" + id);
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Warehouse findById(int id) {
        try ( ResultSet result = connector.getConnection().createStatement().executeQuery("select * from warehouses where warehouse_id = " + id)){
            if (result.next())
                return new Warehouse(result.getInt("warehouse_id"), result.getString("name"), result.getString("address"), result.getString("phone"));
            else
                return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Warehouse> findAll() {
        final List<Warehouse> warehouses = new ArrayList<>();
        try ( ResultSet result = connector.getConnection().prepareStatement("select * from warehouses").executeQuery() ){
            while (result.next())
                warehouses.add(new Warehouse(result.getInt("warehouse_id"), result.getString("name"), result.getString("address"), result.getString("phone")));

            return warehouses;
        } catch (SQLException e) {
            e.printStackTrace();
            return warehouses;
        }
    }
}
