package com.bonnlee.tank;

import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {
    private PropertiesManager() {
    }

    private static  class getProperties{
        private static final Properties instance = new Properties();
    }

    public static Object load(String path,String key){
        Properties properties = getProperties.instance;
        try {
            properties.load(ClassLoader.getSystemResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.get(key);
    }
}
