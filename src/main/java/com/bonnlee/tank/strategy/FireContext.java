//package com.bonnlee.tank.strategy;
//
//import com.bonnlee.tank.Tank;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.util.HashMap;
//import java.util.Map;
//
//@Component
//public class FireContext {
//    @Autowired
//    private DefaultFire defaultFire;
//
//    private static final Map<String,FireStrategy> FIRE_STRATEGY_MAP = new HashMap<>();
//
//    @PostConstruct
//    private void init(){
//        FIRE_STRATEGY_MAP.put("1",defaultFire);
//    }
//
//    public void fire(Tank tank){
//        FireStrategy fireStrategy = FIRE_STRATEGY_MAP.get(tank.getFireStrategy());
//        fireStrategy.fire(tank);
//    }
//}
