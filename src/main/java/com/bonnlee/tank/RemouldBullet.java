package com.bonnlee.tank;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RemouldBullet extends BaseBullet {

    int x,y;
    TankFrame tankFrame = null;  //引用上层
    DirectionEnum dir;

    private GroupEnum group = GroupEnum.BAD;

    private static final int SPEED = 6;
    public static int HEIGHT = ResourceManager.bullectU.getHeight();
    public static int WIDTH = ResourceManager.bullectU.getWidth();

    boolean isLived = true;  //判断子弹什么时候消失

    public Rectangle rectangle = new Rectangle();

//    public DefaultBullet(int x, int y, DirectionEnum dir) {
//        this.x = x;
//        this.y = y;
//        this.dir = dir;
//    }

    public RemouldBullet(int x, int y, DirectionEnum dir, GroupEnum group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tankFrame = tankFrame;

        rectangle.setRect(x,y,WIDTH,HEIGHT);

        this.tankFrame.bullets.add(this);

        if (group == GroupEnum.GOOD)
            new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public DirectionEnum getDir() {
        return dir;
    }

    public void setDir(DirectionEnum dir) {
        this.dir = dir;
    }

    public GroupEnum getGroup() {
        return group;
    }

    public void setGroup(GroupEnum group) {
        this.group = group;
    }

    @Override
    public void paint(Graphics graphics) {
        //考虑tank的顺序，由于用的是同一个graphics对象，而这个对象在tank类调用时默认的为black，
        //所以需要先设置color，再填充bullet
        if(!isLived){
            tankFrame.bullets.remove(this);
        }

        Color color = graphics.getColor();
        graphics.setColor(Color.RED);
        graphics.fillOval(x,y,WIDTH,HEIGHT);  //设置子弹为 圆形
        graphics.setColor(color);
//        BufferedImage bf = null;
//        switch(dir){
//            case LEFT:
//                bf = ResourceManager.bullectL;
//                break;
//            case RIGHT:
//                bf = ResourceManager.bullectR;
//                break;
//            case UP:
//                bf = ResourceManager.bullectU;
//                break;
//            case DOWN:
//                bf = ResourceManager.bullectD;
//                break;
//        }
//        graphics.drawImage(bf,x,y,null);

        move();
    }

    private void move() {

        switch(dir){
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }

        if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT)
            isLived = false;

        rectangle.setLocation(this.x,this.y);

    }

    @Override
    public void collideWith(BaseTank tank) {
        //判断子弹是否是自己方打出的
        if (this.group == tank.group) return;

        Rectangle tankRect = tank.rectangle;
        if (rectangle.intersects(tankRect)){  //两矩形相交 证明相撞
            tank.die();
            this.die();
            //相交后 发生爆炸
            int bX = tank.x + DefaultTank.WIDTH / 2 - DefaultExplode.WIDTH / 2;
            int bY = tank.y + DefaultTank.HEIGHT / 2 - DefaultExplode.HEIGHT / 2;
            tankFrame.explodes.add(tankFrame.gf.createExplodes(bX,bY,tankFrame));
        }
    }

    private void die() {
        this.isLived = false;
    }


}
