package com.ohenzy.warehouse.storage;

import com.ohenzy.warehouse.models.Warehouse;
import com.ohenzy.warehouse.storage.settings.Connector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WarehouseStorage {

    private final Connector connector = Connector.getInstance();

    public WarehouseStorage(){
        this.createTable();
    }

    public void save(Warehouse warehouse){
        try(PreparedStatement statement = connector.getConnection().prepareStatement("insert into warehouse (name, address, phone) values (?,?,?)")){
            statement.setString(1,warehouse.getName());
            statement.setString(2,warehouse.getAddress());
            statement.setString(3,warehouse.getPhone());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean deleteById(String deleteId){
        if(!deleteId.equals(""))
            if(existsById(Integer.parseInt(deleteId))) {
                try (PreparedStatement statement = connector.getConnection().prepareStatement("delete from warehouse where id=(?)")){
                    statement.setInt(1,Integer.parseInt(deleteId));
                    statement.executeUpdate();
                    return true;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        return false;
    }

    public boolean existsById(int id){
        boolean exists = false;
        try (PreparedStatement statement = connector.getConnection().prepareStatement("select * from warehouse where id=(?)")){
            statement.setInt(1, id);
            exists = statement.executeQuery().next();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return exists;
    }

    public List<Warehouse> findAll(){
        List<Warehouse> warehouses = new ArrayList<>();
        try ( ResultSet result = connector.getConnection().prepareStatement("select * from warehouse").executeQuery() ){
            while (result.next())
                warehouses.add(new Warehouse(result.getInt("id"), result.getString("name"), result.getString("address"), result.getString("phone")));
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return warehouses;
    }


    private void createTable() {
        try {
            if( !tableExists() )
                connector.getConnection().createStatement().executeUpdate("create table warehouse ( id int not null auto_increment primary key, name varchar (50), address varchar (50), phone varchar (50));");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean tableExists() throws SQLException{
        boolean tableExists = false;
        ResultSet result = connector.getConnection().createStatement().executeQuery("CHECK TABLE warehouse");
        while (result.next())
            if (result.getString("Msg_text").equals("OK"))
                tableExists = true;

        return tableExists;
    }

}
