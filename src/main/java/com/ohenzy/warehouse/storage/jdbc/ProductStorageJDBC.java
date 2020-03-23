package com.ohenzy.warehouse.storage.jdbc;

import com.ohenzy.warehouse.models.Product;
import com.ohenzy.warehouse.storage.Storage;
import com.ohenzy.warehouse.storage.util.ConnectorJDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProductStorageJDBC implements Storage<Product> {

    private final ConnectorJDBC connector = ConnectorJDBC.getInstance();

    public ProductStorageJDBC(){
        this.createTable();
    }

    private void createTable() {
        try {
            if( !tableExists() )
                connector.getConnection().createStatement().executeUpdate("create table products ( product_id int not null auto_increment primary key, name varchar (50), quantity int not null, unit varchar (50));");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean tableExists() throws SQLException{
        boolean tableExists = false;
        ResultSet result = connector.getConnection().createStatement().executeQuery("CHECK TABLE products");
        while (result.next())
            if (result.getString("Msg_text").equals("OK"))
                tableExists = true;

        return tableExists;
    }

    @Override
    public void save(Product addingProduct) {
        final Product product = findSimilar(addingProduct);
        if(product != null){
            try (Statement statement = connector.getConnection().createStatement()){
                int quantity = product.getQuantity() + addingProduct.getQuantity();
                if (quantity > 0)
                    statement.execute("update products set quantity = " + quantity +" where name ='"+ product.getName() +"' and unit = '" + product.getUnit() +"';");
                else if (quantity == 0)
                    statement.execute("delete from products where product_id = " + product.getId());
            } catch (SQLException e){
                e.printStackTrace();
            }
        } else {
            try (PreparedStatement statement = connector.getConnection().prepareStatement("insert into products (name, quantity, unit) values (?, ?, ?);")){
                statement.setString(1, addingProduct.getName());
                statement.setInt(2, addingProduct.getQuantity());
                statement.setString(3, addingProduct.getUnit());
                statement.executeUpdate();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteById(int id) {
        try {
            connector.getConnection().createStatement().executeUpdate("delete from products where product_id = " + id);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean existsById(int id) {
        try {
            return connector.getConnection().createStatement().executeQuery("select * from products where product_id = " + id).next();
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Product> findAll() {
        final List<Product> units = new ArrayList<>();
        try(ResultSet result  = connector.getConnection().createStatement().executeQuery("select * from products")){
            while (result.next())
                units.add(new Product(result.getInt("product_id"),result.getString("name"), result.getInt("quantity"),result.getString("unit")));
        }catch (SQLException e){
            e.printStackTrace();
            return units;
        }
        return units;
    }

    @Override
    public void deleteAll() {
    }

    @Override
    public Product findById(int id) {
        return null;
    }

    private Product findSimilar(Product product){
        Product productSimilar = null;
        try (PreparedStatement statement = connector.getConnection().prepareStatement("select * from products where name = (?) and unit = (?)")) {
            statement.setString(1, product.getName());
            statement.setString(2, product.getUnit());
            ResultSet result = statement.executeQuery();
            if (result.next())
                productSimilar = new Product(result.getInt("product_id"), result.getString("name"), result.getInt("quantity"), result.getString("unit"));
        } catch (SQLException e){
            e.printStackTrace();
            return productSimilar;
        }
        return productSimilar;
    }
}
