package com.bonnlee.tank;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class DefaultExplode extends BaseExplodes{
    int x,y;
    TankFrame tankFrame = null;  //引用上层


    public static int HEIGHT = ResourceManager.explodes[0].getHeight();
    public static int WIDTH = ResourceManager.explodes[0].getWidth();
    private static final int SPEED = 2;


    public DefaultExplode(int x, int y, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;

        new Thread(() -> new Audio("audio/explode.wav").play()).start();
    }

    private int explodeStep;


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


    @Override
    public void paint(Graphics graphics) {

        graphics.drawImage(ResourceManager.explodes[explodeStep++],x,y,null);
        if (explodeStep >= ResourceManager.explodes.length)
            tankFrame.explodes.remove(this);

    }

}
