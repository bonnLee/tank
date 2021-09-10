package com.bonnlee.tank.chainofresponsibility;

import com.bonnlee.tank.GameObject;

public interface Collider {
    void doCollide(GameObject o1,GameObject o2);
}
