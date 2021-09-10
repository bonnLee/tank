package com.bonnlee.tank.chainofresponsibility;

import com.bonnlee.tank.Explode;
import com.bonnlee.tank.GameObject;
import com.bonnlee.tank.Tank;

public class TankTankCollider implements Collider{

    /*
    tank和tank 碰撞检测
     */
    @Override
    public void doCollide(GameObject o1, GameObject o2) {
        if(o1 instanceof Tank && o2 instanceof Tank){
            Tank tank1 = (Tank) o1;
            Tank tank2 = (Tank) o2;
            if (tank1.rectangle.intersects(tank2.rectangle)){  //两矩形相交 证明相撞
                tank1.stop();
                tank2.stop();
            }
        }
    }
}
