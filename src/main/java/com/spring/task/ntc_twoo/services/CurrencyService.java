package com.spring.task.ntc_twoo.services;

import java.time.LocalDateTime;

public interface CurrencyService {
    float getCurrencyServiceForStack(String stack, LocalDateTime data);
    String getId();
}
