package com.bonnlee.tank;


public class DefaultFire implements FireStrategy {
    @Override
    public void fire(BaseTank tank) {
        int bullect_X = tank.x + tank.WIDTH/ 2 - DefaultBullet.WIDTH / 2 ;
        int bullect_Y = tank.y + tank.HEIGHT / 2 - DefaultBullet.HEIGHT / 2;
        tank.tankFrame.gf.createBullet(tank.x,tank.y,tank.dir,tank.group,tank.tankFrame);
    }
}
