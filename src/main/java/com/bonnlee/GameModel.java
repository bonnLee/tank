package com.bonnlee;

import com.bonnlee.tank.*;
import com.bonnlee.tank.strategy.DefaultFire;
import com.bonnlee.tank.strategy.FourDirectionFire;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class GameModel {


    private  int initialTankCount;

    public List<Bullet> bullets = new ArrayList<>();
    public List<Tank> enemyTanks = new ArrayList<>();
    public List<Explode> explodes = new ArrayList<>();

    public Tank myTank = new Tank(200,400, DirectionEnum.UP, GroupEnum.GOOD,new FourDirectionFire(),this);

    public GameModel() {
        Properties prop =  new Properties();
        try {
            prop.load(ClassLoader.getSystemResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        initialTankCount = Integer.valueOf(prop.get("initialTankCount").toString());

        //初始化地方tank
        for (int i = 0; i < this.initialTankCount; i++) {
            enemyTanks.add(new Tank(50+i * 80,100, DirectionEnum.DOWN,GroupEnum.BAD,new DefaultFire(),this));
        }
    }

    public void paint(Graphics graphics){
        //重写的paint方法，在窗口被改变时或者其他对窗口的操作时都会被调用
        //填充矩形方法 从窗口的左上方为起点，往右为x轴，往下为y轴
        //width填充物的宽度，height高度
        Color color = graphics.getColor();
        graphics.setColor(Color.WHITE);
        graphics.drawString("子弹的数量"+bullets.size(),10,60);
        graphics.drawString("敌人的数量"+enemyTanks.size(),10,80);
        graphics.setColor(color);
        myTank.paint(graphics);  //交给tank类 自己绘制


        //             注意：如果用这种增强for循环，那么在bullet实例中进行remove时
//             将会报错，原因是底层的Iterator只支持自身遍历时进行删除，在外层不允许删除
//         for (Bullet bullet : bullets) {
//             bullet.paint(graphics);
//         }

        //碰撞检测：子弹攻击到敌方坦克，不再绘制
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < enemyTanks.size(); j++) {
                bullets.get(i).collideWith(enemyTanks.get(j));
            }
        }

        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(graphics);
        }
//         Iterator<Bullet> iterator = bullets.iterator();
//         while(iterator.hasNext()){
//             Bullet bullect = iterator.next();
//             bullect.paint(graphics);
//         }

        //绘制敌方tank
        for (int i = 0; i < enemyTanks.size(); i++) {
            enemyTanks.get(i).paint(graphics);
        }

        //绘制爆炸效果
        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(graphics);
        }
    }
}
