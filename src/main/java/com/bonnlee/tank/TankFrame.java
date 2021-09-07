package com.bonnlee.tank;

import com.bonnlee.tank.strategy.FourDirectionFire;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TankFrame extends Frame {

    public static final int GAME_WIDTH = 800,GAME_HEIGHT = 600;  //整个游戏界面的宽度 和 高度
    private Tank myTank = new Tank(200,400,DirectionEnum.UP,GroupEnum.GOOD,new FourDirectionFire(),this);
    List<Bullet> bullets = new ArrayList<>();
    List<Tank> enemyTanks = new ArrayList<>();
    List<Explode> explodes = new ArrayList<>();

//    public Bullet bullet = new Bullet(300,300,DirectionEnum.DOWN,this);

     public TankFrame(){
         this.setTitle("Tank War");
         this.setSize(GAME_WIDTH,GAME_HEIGHT);
         this.setVisible(true);
         this.setResizable(false);

         this.addKeyListener(new MyKeyAdapter());

         this.addWindowListener(new WindowAdapter() {  //添加窗口监听器 用来监听桌面
             @Override
             public void windowClosing(WindowEvent e) {
                 System.exit(0);  //目的是实现点击窗口叉号可以关闭，否则只能点程序关闭的按钮
             }
         });
     }

     Image offScreenImage = null;
     @Override
     public void update(Graphics g){
         if(offScreenImage == null)
             offScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);

         Graphics gOffScreen = offScreenImage.getGraphics();
         /*
         双缓冲，一般在游戏引擎中都进行了封装
         理解：1.gOffScreen先绘图，将目标图片绘制完成后
                2.通过g 将1中画出的图片整个绘制在g的图片中
          */
         Color color = gOffScreen.getColor();  //先将起始页面的颜色拿出保存(显存中)
         gOffScreen.setColor(Color.BLACK);
         gOffScreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
         gOffScreen.setColor(color);
         paint(gOffScreen);
         g.drawImage(offScreenImage,0,0,null);
     }

     @Override
    public void paint(Graphics graphics){
         //重写的paint方法，在窗口被改变时或者其他对窗口的操作时都会被调用
         //填充矩形方法 从窗口的左上方为起点，往右为x轴，往下为y轴
         //width填充物的宽度，height高度
         Color color = graphics.getColor();
         graphics.setColor(Color.WHITE);
         graphics.drawString("子弹的数量"+bullets.size(),10,60);
         graphics.drawString("敌人的数量"+enemyTanks.size(),10,80);
         graphics.setColor(color);
         myTank.paint(graphics);  //交给tank类 自己绘制


 //             注意：如果用这种增强for循环，那么在bullet实例中进行remove时
//             将会报错，原因是底层的Iterator只支持自身遍历时进行删除，在外层不允许删除
//         for (Bullet bullet : bullets) {
//             bullet.paint(graphics);
//         }

         //碰撞检测：子弹攻击到敌方坦克，不再绘制
         for (int i = 0; i < bullets.size(); i++) {
             for (int j = 0; j < enemyTanks.size(); j++) {
                 bullets.get(i).collideWith(enemyTanks.get(j));
             }
         }

         for (int i = 0; i < bullets.size(); i++) {
             bullets.get(i).paint(graphics);
         }
//         Iterator<Bullet> iterator = bullets.iterator();
//         while(iterator.hasNext()){
//             Bullet bullect = iterator.next();
//             bullect.paint(graphics);
//         }

         //绘制敌方tank
         for (int i = 0; i < enemyTanks.size(); i++) {
             enemyTanks.get(i).paint(graphics);
         }

         //绘制爆炸效果
         for (int i = 0; i < explodes.size(); i++) {
             explodes.get(i).paint(graphics);
         }



     }

     //键盘监听器，其他地方用不到，所以定义为内部类，只供本类使用
     class MyKeyAdapter extends KeyAdapter{
         //通过4个布尔值 判断tank的方向
         boolean bL = false;  //左
         boolean bR = false;  //右
         boolean bU = false;  //上
         boolean bD = false;  //下
         @Override
         public void keyPressed(KeyEvent e) {  //键盘按下触发
             int keyCode = e.getKeyCode();  //每个键盘操作对应的有值
             switch (keyCode){  //根据值匹配操作
                 case KeyEvent.VK_LEFT:
                     bL = true;
                     break;
                 case KeyEvent.VK_RIGHT:
                     bR = true;
                     break;
                 case KeyEvent.VK_UP:
                     bU = true;
                     break;
                 case KeyEvent.VK_DOWN:
                     bD = true;
                     break;

                 default:
                     break;
             }
            setMainTankDirect();
         }

         @Override
         public void keyReleased(KeyEvent e) {  //键盘抬起触发
             int keyCode = e.getKeyCode();
             switch (keyCode){
                 case KeyEvent.VK_LEFT:
                     bL = false;
                     break;
                 case KeyEvent.VK_RIGHT:
                     bR = false;
                     break;
                 case KeyEvent.VK_UP:
                     bU = false;
                     break;
                 case KeyEvent.VK_DOWN:
                     bD = false;
                     break;
                 case KeyEvent.VK_SPACE:
                     myTank.fire();
                     break;


             }
             setMainTankDirect();
         }

         //设置主tank的方向
         private void setMainTankDirect() {
             if(!bL && !bR &&!bU &&!bD ){
                 myTank.setMoving(false);
             }else{
                 myTank.setMoving(true);
                 if(bL) myTank.setDir(DirectionEnum.LEFT);
                 if(bR) myTank.setDir(DirectionEnum.RIGHT);
                 if(bU) myTank.setDir(DirectionEnum.UP);
                 if(bD) myTank.setDir(DirectionEnum.DOWN);
             }
         }


     }
}
