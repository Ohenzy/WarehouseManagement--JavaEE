package com.ohenzy.warehouse.storage;


import com.ohenzy.warehouse.models.Unit;
import com.ohenzy.warehouse.storage.settings.Settings;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UnitStorage {

    private Connection connection;

    public UnitStorage(){
        Settings settings = Settings.getInstance();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(settings.getValues("url"),settings.getValues("username"),settings.getValues("password"));
            this.createTable();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void save(Unit unit){
        try(PreparedStatement statement = connection.prepareStatement("insert into unit (name) values (?) ")){
            statement.setString(1, unit.getName());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean deleteById(String deleteId){
        if(!deleteId.equals(""))
            if(existsById(Integer.parseInt(deleteId))){
                try (PreparedStatement statement = connection.prepareStatement("delete from unit where id=(?)")){
                    statement.setInt(1, Integer.parseInt(deleteId));
                    statement.executeUpdate();
                    return true;
                } catch (SQLException e){
                    e.printStackTrace();
                    return false;
                }
            }
        return false;
    }

    public List<Unit> findAll(){
        final List<Unit> units = new ArrayList<>();
        try(ResultSet result  = connection.createStatement().executeQuery("select * from unit")){
            while (result.next())
                units.add(new Unit(result.getInt("id"),result.getString("name")));
        }catch (SQLException e){
            e.printStackTrace();
        }
        return units;
    }

    public boolean existsById(int id) {
        boolean exists = false;
        try (PreparedStatement statement = connection.prepareStatement("select * from unit where id=(?)")){
            statement.setInt(1, id);
            exists = statement.executeQuery().next();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return exists;
    }

    private void createTable() {
        try {
            if( !tableExists() )
                connection.createStatement().executeUpdate("create table unit ( id int not null auto_increment primary key, name varchar (50));");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean tableExists() throws SQLException{
        boolean tableExists = false;
        ResultSet result = connection.createStatement().executeQuery("CHECK TABLE unit");
        while (result.next())
            if (result.getString("Msg_text").equals("OK"))
                tableExists = true;

        return tableExists;
    }

}
