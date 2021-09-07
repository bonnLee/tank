package com.bonnlee.tank.strategy;

import com.bonnlee.tank.Bullet;
import com.bonnlee.tank.DirectionEnum;
import com.bonnlee.tank.Tank;

public class FourDirectionFire implements FireStrategy {
    @Override
    public void fire(Tank tank) {
        int bullect_X = tank.getX() + tank.WIDTH/ 2 - Bullet.WIDTH / 2 ;
        int bullect_Y = tank.getY() + tank.HEIGHT / 2 - Bullet.HEIGHT / 2;

        DirectionEnum[] values = DirectionEnum.values();
        for (DirectionEnum value : values) {
            new Bullet(bullect_X, bullect_Y, value,tank.getGroup(),tank.getTankFrame());
        }
    }
}
