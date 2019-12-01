package com.godx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.godx.mapper")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }
}
