package com.bonnlee.tank;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class TankRunner implements ApplicationRunner {

    @Value("${initialTankCount}")
    private  Integer initialTankCount;

    @Override
    public void run(ApplicationArguments args) throws Exception{
        TankFrame frame = new TankFrame();

        //如果不是springboot项目，没有从配置文件中读，可以用下面的方式
//       Properties prop =  new Properties();
//       prop.load(ClassLoader.getSystemResourceAsStream("config"));
//       Integer initialTankCount = Integer.valueOf(prop.get("initialTankCount").toString()) ;
       //以上 为new实例的方式 若不同的对象调用则需要new多个实例 ，下面通过封装单例模式 实现
        Object load = PropertiesManager.load("config", "initialTankCount");
        System.out.println(load.toString());

//       System.out.println(initialTankCount);
        //背景音乐
//        new Thread(() -> new Audio("audio/war1.wav").loop()).start();

        for (int i = 0; i < this.initialTankCount; i++) {
            frame.enemyTanks.add(new DefaultTank(50+i * 80,100, DirectionEnum.DOWN,GroupEnum.BAD,new DefaultFire(),frame));
        }

        while (true){
            Thread.sleep(50);
            //  repaint();  //Frame或其超类的方法，window会直接重新调用paint绘图
            frame.repaint();
        }
    }
}
