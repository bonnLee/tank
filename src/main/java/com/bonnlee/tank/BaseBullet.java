package com.bonnlee.tank;

import java.awt.*;

public abstract class BaseBullet {
    abstract void paint(Graphics graphics);

    public abstract void collideWith(BaseTank defaultTank);
}
