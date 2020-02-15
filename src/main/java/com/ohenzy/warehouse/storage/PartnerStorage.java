package com.ohenzy.warehouse.storage;

import com.ohenzy.warehouse.models.Partner;
import com.ohenzy.warehouse.storage.settings.Connector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PartnerStorage  {

    private Connector connector = Connector.getInstance();


    public PartnerStorage() {
       this.createTable();
    }

    public boolean deleteById(String deleteId) {
        if(deleteId.equals(""))
            return false;
        else {
            int id = Integer.parseInt(deleteId);
            if (!existsById(id))
                return false;
            else {
                try (PreparedStatement statement = connector.getConnection().prepareStatement("delete from partners where id = (?)")) {
                    statement.setInt(1, id);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return true;
            }
        }
    }

    public void save(Partner partner) {
        existsById(partner.getId());
        try (PreparedStatement statement = connector.getConnection().prepareStatement("insert into partners (name_organisation, name_director, address, phone, email, INN, OGRN) values (?, ?, ?, ?, ?, ?, ?)")){
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

    public Partner findById(int id) {
        Partner partner = null;
        try (PreparedStatement statement = connector.getConnection().prepareStatement("select * from partners where id = (?)")){
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            while (result.next())
                partner = new Partner(result.getInt("id"), result.getString("name_organisation"), result.getString("name_director"),
                        result.getString("address"), result.getString("phone"), result.getString("email"), result.getString("INN"),
                        result.getString("OGRN"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return partner;
    }

    public boolean existsById(int id) {
        boolean exists = false;
        try (PreparedStatement statement = connector.getConnection().prepareStatement("select * from partners where id = (?)")){
            statement.setInt(1, id);
            exists = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    public List<Partner> findAll() {
        final List<Partner> partners = new ArrayList();
        try (ResultSet result = connector.getConnection().createStatement().executeQuery("select * from partners")){
            while (result.next()) {
                partners.add(new Partner(result.getInt("id"), result.getString("name_organisation"), result.getString("name_director"),
                        result.getString("address"), result.getString("phone"), result.getString("email"), result.getString("INN"),
                        result.getString("OGRN")));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return partners;
    }

    private void createTable() {
        try {
            if( !tableExists() )
                connector.getConnection().createStatement().executeUpdate("create table partners ( id int not null auto_increment primary key, name_organisation varchar (50)," +
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
}
