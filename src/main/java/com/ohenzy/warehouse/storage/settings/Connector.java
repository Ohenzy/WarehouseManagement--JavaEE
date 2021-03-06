package com.ohenzy.warehouse.storage.settings;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connector {

    private static final Connector INSTANCE = new Connector();
    private final Properties properties = new Properties();
    private Connection connection;

    private Connector() {
        try {
            properties.load(new FileInputStream(getClass().getClassLoader().getResource("mysql.properties").getFile()));
            Class.forName("com.mysql.cj.jdbc.Driver");

            this.connection = DriverManager.getConnection(properties.getProperty("url"),properties);
//            this.connection = DriverManager.getConnection(
//                    "jdbc:mysql://127.0.0.1:3306/storagesql?serverTimezone=Europe/Moscow&useSSL=false",
//                    "root",
//                    "2610");
        }  catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connector getInstance(){
        return INSTANCE;
    }

    public Connection getConnection(){
        return connection;
    }


}
