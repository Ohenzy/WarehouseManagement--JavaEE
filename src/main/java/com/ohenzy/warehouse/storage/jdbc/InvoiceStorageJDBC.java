package com.ohenzy.warehouse.storage.jdbc;

import com.ohenzy.warehouse.models.Invoice;
import com.ohenzy.warehouse.models.InvoiceProduct;
import com.ohenzy.warehouse.storage.Storage;
import com.ohenzy.warehouse.storage.util.ConnectorJDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class InvoiceStorageJDBC implements Storage<Invoice> {


    private final ConnectorJDBC connector = ConnectorJDBC.getInstance();
    private final InvoiceProductStorage products;

    public InvoiceStorageJDBC(){
        products = new InvoiceProductStorage();
        this.createTable();
    }

    private void createTable() {
        if(!existsTable())
            try {
                connector.getConnection().createStatement().executeUpdate(
                        "create table invoices (" +
                                "invoice_id int primary key auto_increment, " +
                                "date bigint, " +
                                "type varchar(10), " +
                                "partner_id int not null, " +
                                "FOREIGN KEY (partner_id) REFERENCES partners(partner_id));"
                );
            }catch (SQLException e){
                e.printStackTrace();
            }
    }

    private boolean existsTable() {
        boolean tableExists = false;
        try(ResultSet result = connector.getConnection().createStatement().executeQuery("check table invoices;")){
            while (result.next())
                if (result.getString("Msg_text").equals("OK"))
                    tableExists = true;

        } catch (SQLException e){
            e.printStackTrace();
        }
        return tableExists;
    }

    @Override
    public void save(Invoice invoice) {
        try (PreparedStatement statement = connector.getConnection().prepareStatement("insert invoices (date, type, partner_id ) values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, invoice.getDate().getTime());
            statement.setString(2, invoice.getType());
            statement.setInt(3, invoice.getPartner().getId());
            statement.executeUpdate();
            ResultSet result = statement.getGeneratedKeys();
            int invoiceId = 0;
            while (result.next())
                invoiceId = result.getInt(1);
            products.addAll(invoice.getProducts(), invoiceId);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Invoice findById(int id) {
        return null;
    }

    @Override
    public List<Invoice> findAll() {
        final List<Invoice> invoices = new ArrayList<>();
        try (ResultSet result = connector.getConnection().createStatement().executeQuery("select * from invoices;")){
            while (result.next())
                invoices.add(new Invoice(
                        result.getInt("invoice_id"),
                        new Date(result.getLong("date")),
                        result.getString("type"),
                        new PartnerStorageJDBC().findById(result.getInt("partner_id")),
                        products.findAllByInvoiceId(result.getInt("invoice_id")))
                );
            return invoices;
        } catch (SQLException e){
            e.printStackTrace();
            return invoices;
        }
    }

    @Override
    public boolean existsById(int id) {
        try {
           return connector.getConnection().createStatement().executeQuery("select * from invoices where invoice_id = " + id).next();
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void deleteById(int id) {
        if(products.deleteAllByInvoiceId(id)){
            try  {
                connector.getConnection().createStatement().executeUpdate("delete from invoices where invoice_id = " + id);
            } catch ( SQLException e ){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteAll() {
        try {
            if(existsTable())
                connector.getConnection().createStatement().executeUpdate("drop table invoice_products, invoices;");
            this.createTable();
            products.createTable();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private class InvoiceProductStorage {

        public InvoiceProductStorage(){
            this.createTable();
        }


        private boolean deleteAllByInvoiceId(int invoiceId){
            try  {
                connector.getConnection().createStatement().executeUpdate("delete from invoice_products where invoice_id = " + invoiceId);
                return true;
            } catch ( SQLException e ){
                e.printStackTrace();
                return false;
            }
        }

        private void addAll(final List<InvoiceProduct> products, int invoiceId){
            for(InvoiceProduct product : products){
                try (PreparedStatement statement = connector.getConnection().prepareStatement("insert invoice_products (name, quantity, unit, price, warehouse_id, invoice_id) values (?, ?, ?, ?, ?, ?)")){
                    statement.setString(1, product.getName());
                    statement.setInt(2, product.getQuantity());
                    statement.setString(3, product.getUnit());
                    statement.setInt(4, product.getPrice());
                    statement.setInt(5, product.getWarehouse().getId());
                    statement.setInt(6, invoiceId);
                    statement.executeUpdate();
                } catch (SQLException e){
                    e.printStackTrace();
                }

            }
        }

        private List<InvoiceProduct> findAllByInvoiceId(int invoice_id){
            final List<InvoiceProduct> products = new ArrayList<>();
            try (PreparedStatement statement = connector.getConnection().prepareStatement("select * from invoice_products where invoice_id = ?")){
                statement.setInt(1, invoice_id);
                ResultSet result = statement.executeQuery();
                while (result.next())
                    products.add(new InvoiceProduct(
                            result.getInt("invoice_product_ic"),
                            result.getInt("invoice_id"),
                            result.getString("name"),
                            result.getInt("quantity"),
                            result.getString("unit"),
                            result.getInt("price"),
                            new WarehouseStorageJDBC().findById(result.getInt("warehouse_id"))
                    ));

            } catch (SQLException e){
                e.printStackTrace();
            }

            return products;
        }

        private void createTable(){
            if(!existsTable())
                try{
                    connector.getConnection().createStatement().executeUpdate(
                            "create table invoice_products (" +
                                    "invoice_product_ic int primary key not null auto_increment, " +
                                    "name varchar (50)," +
                                    "quantity int," +
                                    "unit varchar (50)," +
                                    "price int," +
                                    "warehouse_id int," +
                                    "invoice_id int," +
                                    "foreign key (warehouse_id) references warehouses (warehouse_id)," +
                                    "foreign key (invoice_id) references invoices (invoice_id))"
                    );
                } catch (SQLException e){
                    e.printStackTrace();
                }
        }

        private boolean existsTable(){
            boolean existedTable = false;
            try (ResultSet result = connector.getConnection().createStatement().executeQuery("check table invoice_products")){
                while (result.next())
                    if(result.getString("Msg_text").equals("OK"))
                        existedTable = true;

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return existedTable;
        }
    }
}
