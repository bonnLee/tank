package com.bonnlee.tank;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Test01 {
    @Value("${initialTankCount}")
    private String initialTankCount;

    @Test
    public void test01(){
        System.out.println(initialTankCount);
    }
}
