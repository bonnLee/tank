package com.bonnlee.tank;

import java.awt.*;

public abstract class BaseTank {
   public TankFrame tankFrame = null;  //引用上层

   public int x,y,speed;

   DirectionEnum dir;

   public static int HEIGHT = ResourceManager.goodtankU.getHeight();
   public static int WIDTH = ResourceManager.goodtankU.getWidth();

   public boolean moving = true;
   public boolean isLived = true;

   public GroupEnum group = GroupEnum.BAD;

   abstract void paint(Graphics graphics);

   public Rectangle rectangle = new Rectangle();

   public void die() {
      this.isLived = false;
   }
}
