package com.bonnlee.tank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TankApplication {


    /*
    Caused by: java.awt.HeadlessException: null
    可能会出现上述异常：解决方式，VM options 添加 -Djava.awt.headless=false
     */

    public static void main(String[] args) {
        SpringApplication.run(TankApplication.class, args);
    }


}
