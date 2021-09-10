package com.bonnlee.tank;

import com.bonnlee.tank.strategy.GameModel;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class TankFrame extends Frame {
    GameModel gm = new GameModel();

    public static final int GAME_WIDTH = 800,GAME_HEIGHT = 600;  //整个游戏界面的宽度 和 高度


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
        gm.paint(graphics);
     }

     /*
     这里理解为MVC中的 V 和 C，frame呈现窗口同时也可以进行控制，
     按键和控制我方tank移动 不放在GameModel中
      */
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
                     gm.myTank.fire();
                     break;


             }
             setMainTankDirect();
         }

         //设置主tank的方向
         private void setMainTankDirect() {
             if(!bL && !bR &&!bU &&!bD ){
                 gm.myTank.setMoving(false);
             }else{
                 gm.myTank.setMoving(true);
                 if(bL) gm.myTank.setDir(DirectionEnum.LEFT);
                 if(bR) gm.myTank.setDir(DirectionEnum.RIGHT);
                 if(bU) gm.myTank.setDir(DirectionEnum.UP);
                 if(bD) gm.myTank.setDir(DirectionEnum.DOWN);
             }
         }
     }
}
