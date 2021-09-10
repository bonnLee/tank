package com.bonnlee.tank;

import lombok.Data;

import java.awt.*;

@Data
public abstract class GameObject {
    public int x,y;

    public GameModel gm;
    public abstract void paint(Graphics graphics);
}
