package com.spring.task.ntc_twoo.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.spring.task.ntc_twoo.controller", "com.spring.task.ntc_twoo.services"})
public class NtcTwooApplication {

    public static void main(String[] args) {
        SpringApplication.run(NtcTwooApplication.class, args);
    }

}
