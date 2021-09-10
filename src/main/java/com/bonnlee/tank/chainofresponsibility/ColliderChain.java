package com.bonnlee.tank.chainofresponsibility;

import com.bonnlee.tank.GameObject;

import java.util.LinkedList;
import java.util.List;

public class ColliderChain implements Collider{
    /*
    用linkedlist原因：底层是链表，每增加一个会在末尾添加一个，避免资源浪费
    而arrayList底层是数组，会默认开辟一定空间，如果数据少，其他空间处于闲置状态
     */
    private List<Collider> allColliderList = new LinkedList<>();

    public ColliderChain() {
        allColliderList.add(new TankBulletCollider());
        allColliderList.add(new TankTankCollider());
    }


    @Override
    public void doCollide(GameObject o1, GameObject o2) {
        for (Collider collider : allColliderList) {
            collider.doCollide(o1,o2);
        }
    }
}
