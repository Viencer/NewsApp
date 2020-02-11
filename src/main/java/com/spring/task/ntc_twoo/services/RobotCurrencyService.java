package com.spring.task.ntc_twoo.services;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RobotCurrencyService implements CurrencyService {
    @Override
    public float getCurrencyServiceForStack(String stack, LocalDateTime data) {
        System.out.println("Robot stack: " + stack + " data: " + data);
        return 0;
    }

    @Override
    public String getId() {
        return "Robot";
    }
}
