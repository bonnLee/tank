//package com.bonnlee.tank;
//
//import org.springframework.beans.factory.annotation.Value;
//
//public class Main {
//
//
//    public static void main(String[] args) throws InterruptedException {
//        TankFrame frame = new TankFrame();
//
////        System.out.println(initialTankCount);
//
//        //背景音乐
////        new Thread(() -> new Audio("audio/war1.wav").loop()).start();
//
//        for (int i = 0; i < 5; i++) {
//            frame.enemyTanks.add(new Tank(50+i * 80,100, DirectionEnum.DOWN,GroupEnum.BAD,frame));
//        }
//
//        while (true){
//            Thread.sleep(50);
//            //  repaint();  //Frame或其超类的方法，window会直接重新调用paint绘图
//            frame.repaint();
//        }
//    }
//
//}
