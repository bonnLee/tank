package com.bonnlee.tank;

//import com.bonnlee.tank.strategy.FireContext;
import com.bonnlee.GameModel;
import com.bonnlee.tank.strategy.FireStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;


public class Tank {

    int x,y,speed;
    public GameModel gm = null;  //引用上层

    DirectionEnum dir;

    public boolean moving = true;
    public boolean isLived = true;

    private GroupEnum group = GroupEnum.BAD;

    public static int HEIGHT = ResourceManager.goodtankU.getHeight();
    public static int WIDTH = ResourceManager.goodtankU.getWidth();
    private static final int SPEED = 2;

    private Random random = new Random();

    private FireStrategy fireStrategy;

    public Rectangle rectangle = new Rectangle();

    public Tank(int x, int y, DirectionEnum dir, GroupEnum group, FireStrategy fireStrategy, GameModel gameModel) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.fireStrategy = fireStrategy;
        this.gm = gameModel;

        //初始化自身的rectangle
        rectangle.setRect(x,y,WIDTH,HEIGHT);
    }

    public FireStrategy getFireStrategy() {
        return fireStrategy;
    }

    public void setFireStrategy(FireStrategy fireStrategy) {
        this.fireStrategy = fireStrategy;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public DirectionEnum getDir() {
        return dir;
    }

    public void setDir(DirectionEnum dir) {
        this.dir = dir;
    }

    public GroupEnum getGroup() {
        return group;
    }

    public void setGroup(GroupEnum group) {
        this.group = group;
    }


    public void paint(Graphics graphics) {
//        Color color = graphics.getColor();
//        graphics.setColor(Color.RED);
//        graphics.fillRect(x,y,50,50);
//        graphics.setColor(color);
        if (!isLived)
            gm.enemyTanks.remove(this);
        BufferedImage bf = null;
        switch(dir){
            case LEFT:
                bf = this.group == GroupEnum.GOOD ? ResourceManager.goodtankL : ResourceManager.badtankL;
                break;
            case RIGHT:
                bf = this.group == GroupEnum.GOOD ? ResourceManager.goodtankR : ResourceManager.badtankR;
                break;
            case UP:
                bf = this.group == GroupEnum.GOOD ? ResourceManager.goodtankU : ResourceManager.badtankU;
                break;
            case DOWN:
                bf = this.group == GroupEnum.GOOD ? ResourceManager.goodtankD : ResourceManager.badtankD;
                break;
        }
        graphics.drawImage(bf,x,y,null);

        move();


    }

    private void move() {
        //静止时 不移动tank
        if(!moving) return;

        switch(dir){
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }

        if(this.group == GroupEnum.BAD && random.nextInt(100) > 95) this.fire();

        if(this.group == GroupEnum.BAD && random.nextInt(100) > 95) randomDir();

        //我方tank移动时增加履带声音
//        if(this.group == GroupEnum.GOOD ) new Thread(() -> new Audio("audio/tank_move.wav").play()).start();

        //边界检测
        boundCheck();

        //更新rectangle的位置
        rectangle.setLocation(this.x,this.y);
    }

    private void boundCheck() {
        this.x = this.x > TankFrame.GAME_WIDTH - Tank.WIDTH - 2 ? TankFrame.GAME_WIDTH - Tank.WIDTH - 2 : this.x < 2 ?  2 : this.x;

        this.y = this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2 ? TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2 : this.y < 28 ?  28 : this.y;
    }

    //随机取4个方向
    private void randomDir(){
        this.dir = DirectionEnum.values()[random.nextInt(4)];
    }

    public void fire() {
        fireStrategy.fire(this);
    }

    public void die() {
        this.isLived = false;
    }
}
