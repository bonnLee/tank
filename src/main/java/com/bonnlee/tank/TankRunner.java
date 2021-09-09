package com.bonnlee.tank;

import com.bonnlee.tank.strategy.DefaultFire;
import com.sun.org.apache.xpath.internal.operations.String;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class TankRunner implements ApplicationRunner {



    @Override
    public void run(ApplicationArguments args) throws Exception{
        TankFrame frame = new TankFrame();

        while (true){
            Thread.sleep(25);
            //  repaint();  //Frame或其超类的方法，window会直接重新调用paint绘图
            frame.repaint();
        }

        //如果不是springboot项目，没有从配置文件中读，可以用下面的方式
//       Properties prop =  new Properties();
//       prop.load(ClassLoader.getSystemResourceAsStream("config"));
//       Integer initialTankCount = Integer.valueOf(prop.get("initialTankCount").toString()) ;
       //以上 为new实例的方式 若不同的对象调用则需要new多个实例 ，下面通过封装单例模式 实现
//        Object load = PropertiesManager.load("config", "initialTankCount");
//        System.out.println(load.toString());

//       System.out.println(initialTankCount);
        //背景音乐
//        new Thread(() -> new Audio("audio/war1.wav").loop()).start();




    }
}
