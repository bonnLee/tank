package com.bonnlee.tank.chainofresponsibility;

import com.bonnlee.tank.Bullet;
import com.bonnlee.tank.Explode;
import com.bonnlee.tank.GameObject;
import com.bonnlee.tank.Tank;

import java.awt.*;

public class TankBulletCollider implements Collider {
    /*
    tank 和 bullet 碰撞检测
     */
    @Override
    public void doCollide(GameObject o1, GameObject o2) {
        if(o1 instanceof Tank && o2 instanceof Bullet){
            Tank tank = (Tank) o1;
            Bullet bullet = (Bullet) o2;

            //判断子弹是否是自己方打出的
            if ( tank.getGroup() == bullet.getGroup()) return;

            Rectangle tankRect = tank.rectangle;
            if (bullet.rectangle.intersects(tankRect)){  //两矩形相交 证明相撞
                tank.die();
                bullet.die();
                //相交后 发生爆炸
                int bX = tank.x + Tank.WIDTH / 2 - Explode.WIDTH / 2;
                int bY = tank.y + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;
                tank.gm.objectList.add(new Explode(bX,bY,tank.gm));
            }
        }else if(o1 instanceof Bullet && o2 instanceof Tank){
            doCollide(o2,o1);
        }
    }
}
