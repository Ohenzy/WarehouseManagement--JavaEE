package com.ohenzy.warehouse.storage.jdbc;

import com.ohenzy.warehouse.models.Partner;
import com.ohenzy.warehouse.storage.Storage;
import com.ohenzy.warehouse.storage.util.ConnectorJDBC;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PartnerStorageJDBC implements Storage<Partner> {

    private ConnectorJDBC connector = ConnectorJDBC.getInstance();


    public PartnerStorageJDBC() {
       this.createTable();
    }

    private void createTable() {
        try {
            if( !tableExists() )
                connector.getConnection().createStatement().executeUpdate("create table partners ( partner_id int primary key auto_increment , name_organisation varchar (50)," +
                        " name_director varchar (50),address varchar(50), phone varchar(50)," +
                        "email varchar(50), INN varchar(50),  OGRN varchar(50));");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean tableExists() throws SQLException{
        boolean tableExists = false;
        ResultSet result = connector.getConnection().createStatement().executeQuery("CHECK TABLE partners");
        while (result.next())
            if (result.getString("Msg_text").equals("OK"))
                tableExists = true;

        return tableExists;
    }

    @Override
    public Partner findById(int id) {
        try (ResultSet result = connector.getConnection().createStatement().executeQuery("select * from partners where partner_id = " + id) ){
            if (result.next()) {
                return new Partner(result.getInt("partner_id"), result.getString("name_organisation"), result.getString("name_director"),
                        result.getString("address"), result.getString("phone"), result.getString("email"), result.getString("INN"),
                        result.getString("OGRN"));
            } else return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Partner> findAll() {
        final List<Partner> partners = new ArrayList();
        try (ResultSet result = connector.getConnection().createStatement().executeQuery("select * from partners")){
            while (result.next())
                partners.add(new Partner(result.getInt("partner_id"), result.getString("name_organisation"), result.getString("name_director"),
                        result.getString("address"), result.getString("phone"), result.getString("email"), result.getString("INN"),
                        result.getString("OGRN")));

            return partners;
        } catch (SQLException e) {
            e.printStackTrace();
            return partners;
        }
    }

    @Override
    public boolean existsById(int id) {
        try {
            return connector.getConnection().createStatement().executeQuery("select * from partners where partner_id = " + id).next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void deleteById(int id) {
        try  {
            connector.getConnection().createStatement().executeUpdate("delete from partners where partner_id = " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAll() {
        try{
            if(tableExists())
                connector.getConnection().createStatement().executeUpdate("drop table partners");
        }catch (SQLException e){
            e.printStackTrace();
        }
        createTable();
    }

    @Override
    public void save(Partner partner) {
        if(existsById(partner.getId())){
            try (PreparedStatement statement = connector.getConnection().prepareStatement("update partners set name_organisation = ?, name_director = ?, address = ?, phone = ?, email = ?, INN = ?, OGRN = ? where partner_id = ?")) {
                statement.setString(1, partner.getNameOrganisation());
                statement.setString(2, partner.getNameDirector());
                statement.setString(3, partner.getAddress());
                statement.setString(4, partner.getPhone());
                statement.setString(5, partner.getEmail());
                statement.setString(6, partner.getINN());
                statement.setString(7, partner.getOGRN());
                statement.setInt(8, partner.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try (PreparedStatement statement = connector.getConnection().prepareStatement("insert partners (name_organisation, name_director, address, phone, email, INN, OGRN) values (?, ?, ?, ?, ?, ?, ?)")){
                statement.setString(1,partner.getNameOrganisation());
                statement.setString(2,partner.getNameDirector());
                statement.setString(3,partner.getAddress());
                statement.setString(4,partner.getPhone());
                statement.setString(5,partner.getEmail());
                statement.setString(6,partner.getINN());
                statement.setString(7,partner.getOGRN());
                statement.executeUpdate();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
