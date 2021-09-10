package com.bonnlee.tank;

import lombok.Data;

import java.awt.*;

@Data
public class Explode extends GameObject{

    GameModel gm;

    public static int HEIGHT = ResourceManager.explodes[0].getHeight();
    public static int WIDTH = ResourceManager.explodes[0].getWidth();
    private static final int SPEED = 2;


    public Explode(int x, int y, GameModel gm) {
        this.x = x;
        this.y = y;
        this.gm = gm;

        new Thread(() -> new Audio("audio/explode.wav").play()).start();
    }

    private int explodeStep;


    @Override
    public void paint(Graphics graphics) {
        graphics.drawImage(ResourceManager.explodes[explodeStep++],x,y,null);
        if (explodeStep >= ResourceManager.explodes.length)
            gm.objectList.remove(this);
    }

}
