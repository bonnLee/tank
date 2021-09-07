package com.bonnlee.tank;

public abstract class GameFactory {
    abstract BaseTank createTank(int x, int y, DirectionEnum dir, GroupEnum group, FireStrategy fireStrategy, TankFrame tankFrame);
    abstract BaseBullet createBullet(int x, int y, DirectionEnum dir,GroupEnum group,TankFrame tankFrame);
    abstract BaseExplodes createExplodes(int x, int y, TankFrame tankFrame);
}
