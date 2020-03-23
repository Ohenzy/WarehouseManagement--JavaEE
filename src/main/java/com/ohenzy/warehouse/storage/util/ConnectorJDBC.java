package com.ohenzy.warehouse.storage.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectorJDBC {

    private static final ConnectorJDBC INSTANCE = new ConnectorJDBC();
    private final Properties properties = new Properties();
    private Connection connection;

    private ConnectorJDBC() {
        try {
            properties.load(new FileInputStream(getClass().getClassLoader().getResource("config.properties").getFile()))
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(properties.getProperty("url"),properties);

        }  catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ConnectorJDBC getInstance(){
        return INSTANCE;
    }

    public Connection getConnection(){
        return connection;
    }


}
