package com.bonnlee.tank;

import com.bonnlee.tank.DefaultBullet;
import com.bonnlee.tank.DirectionEnum;
import com.bonnlee.tank.DefaultTank;
import com.bonnlee.tank.FireStrategy;

public class FourDirectionFire implements FireStrategy {
    @Override
    public void fire(BaseTank tank) {
        int bullect_X = tank.x + tank.WIDTH/ 2 - DefaultBullet.WIDTH / 2 ;
        int bullect_Y = tank.y + tank.HEIGHT / 2 - DefaultBullet.HEIGHT / 2;

        DirectionEnum[] values = DirectionEnum.values();
        for (DirectionEnum value : values) {
            tank.tankFrame.gf.createBullet(tank.x,tank.y,value,tank.group,tank.tankFrame);
        }
    }
}
