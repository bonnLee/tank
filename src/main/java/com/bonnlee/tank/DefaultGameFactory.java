package com.bonnlee.tank;

public class DefaultGameFactory extends GameFactory {

    @Override
    BaseTank createTank(int x, int y, DirectionEnum dir, GroupEnum group, FireStrategy fireStrategy, TankFrame tankFrame) {
        return new DefaultTank(x,y,dir,group,fireStrategy,tankFrame);
    }

    @Override
    BaseBullet createBullet(int x, int y, DirectionEnum dir, GroupEnum group, TankFrame tankFrame) {
        return new DefaultBullet(x,y,dir,group,tankFrame);
    }

    @Override
    BaseExplodes createExplodes(int x, int y, TankFrame tankFrame) {
        return new DefaultExplode(x,y,tankFrame);
    }
}
