package com.bonnlee.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceManager {
    public static BufferedImage badtankL,badtankR,badtankU,badtankD;
    public static BufferedImage goodtankL,goodtankR,goodtankU,goodtankD;
    public static BufferedImage bullectL,bullectR,bullectU,bullectD;
    public static BufferedImage[] explodes = new BufferedImage[16];
    static {
        try {
            //敌方tank图片
            badtankU =  ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
            badtankL =  ImageUtil.rotateImage(badtankU,-90);
            badtankR =  ImageUtil.rotateImage(badtankU,90);
            badtankD =  ImageUtil.rotateImage(badtankU,180);
            //我方tank图片
            goodtankU =  ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
            goodtankL =  ImageUtil.rotateImage(goodtankU,-90);
            goodtankR =  ImageUtil.rotateImage(goodtankU,90);
            goodtankD =  ImageUtil.rotateImage(goodtankU,180);
            //bullet图片
            bullectU =  ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
            bullectL =  ImageUtil.rotateImage(bullectU,-90);
            bullectR =  ImageUtil.rotateImage(bullectU,90);
            bullectD =  ImageUtil.rotateImage(bullectU,180);
            //爆炸图片 由16张组成一个动图
            for (int i = 0; i < explodes.length; i++) {
                String explodeImgPath = "images/e"+(i+1)+".gif";
                explodes[i] = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream(explodeImgPath));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//    public static void main(String[] args) {
//        try {
//            bullectU =  ImageIO.read(ResourceManager.class.getClassLoader().getSystemResourceAsStream("images/bulletL.gif"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
