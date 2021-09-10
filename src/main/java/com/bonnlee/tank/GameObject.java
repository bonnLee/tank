package com.bonnlee.tank;

import com.bonnlee.tank.strategy.GameModel;
import lombok.Data;

import java.awt.*;

@Data
public abstract class GameObject {
    int x,y;

    public GameModel gm;
    public abstract void paint(Graphics graphics);
}
