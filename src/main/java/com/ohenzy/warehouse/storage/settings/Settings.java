package com.ohenzy.warehouse.storage.settings;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Settings {

    private static final Settings INSTANCE = new Settings();
    private final Properties properties = new Properties();

    public Settings() {
        try {
            properties.load(new FileInputStream(getClass().getClassLoader().getResource("mysql.properties").getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Settings getInstance(){
        return INSTANCE;
    }

    public String getValues(String key){
        return properties.getProperty(key);
    }
}
