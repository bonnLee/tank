package com.bonnlee.tank;

import java.io.IOException;
import java.util.Properties;

public enum PropertiesManagerEnum {

    /*
    PropertiesManager枚举 单例模式
     */
    Properties;

    public Properties properties;

    PropertiesManagerEnum() {
        properties = new Properties();
    }

    public Properties getInstance(){
        return properties;
    }

    public Object load(String path,String key){
        try {
            properties.load(ClassLoader.getSystemResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.get(key);
    }

    public static void main(String[] args) throws Exception {

        for (int i = 0; i < 100; i++) {
            new Thread(() -> System.out.println(PropertiesManagerEnum.Properties.getInstance().hashCode())).start();
        }
//        System.out.println(PropertiesManagerEnum.Properties.load("config", "initialTankCount"));
    }
}
