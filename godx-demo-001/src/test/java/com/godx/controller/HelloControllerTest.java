package com.godx.controller;

import com.godx.App;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class HelloControllerTest {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    UserController userController;
    @Test
    public void hello() {
        String result = userController.hello();
        logger.info(result);
    }
}