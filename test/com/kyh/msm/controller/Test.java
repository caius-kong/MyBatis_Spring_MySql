package com.kyh.msm.controller;

import com.kyh.msm.service.UserService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by kongyunhui on 16/9/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring.xml", "classpath*:spring-*.xml", "classpath*:mybatis-config.xml"})
public class Test {
    @Autowired
    private UserController userController;

    @org.junit.Test
    public void test1(){
        System.out.println(userController);
    }
}
