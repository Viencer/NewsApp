package com.spring.task.ntc_twoo.services;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NYSECurencyService implements CurrencyService {

    @Override
    public float getCurrencyServiceForStack(String stack, LocalDateTime data) {
        System.out.println("NYCE stack: " + stack + " date " + data);
        return 0;
    }

    @Override
    public String getId() {
        return "NYCE";
    }
}
