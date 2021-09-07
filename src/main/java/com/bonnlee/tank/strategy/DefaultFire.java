package com.bonnlee.tank.strategy;

import com.bonnlee.tank.Bullet;
import com.bonnlee.tank.Tank;
import org.springframework.stereotype.Component;


public class DefaultFire implements FireStrategy {
    @Override
    public void fire(Tank tank) {
        int bullect_X = tank.getX() + tank.WIDTH/ 2 - Bullet.WIDTH / 2 ;
        int bullect_Y = tank.getY() + tank.HEIGHT / 2 - Bullet.HEIGHT / 2;

        new Bullet(bullect_X, bullect_Y, tank.getDir(),tank.getGroup(),tank.getTankFrame());
    }
}
