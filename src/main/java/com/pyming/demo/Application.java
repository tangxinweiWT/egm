package com.pyming.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "com.pyming.demo")
@EntityScan("com.pyming.demo.domain.po")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
