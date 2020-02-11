package com.spring.task.ntc_twoo.controller;

import com.spring.task.ntc_twoo.services.NYSECurencyService;
import com.spring.task.ntc_twoo.services.RobotCurrencyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import java.util.concurrent.*;

@RestController
@RequestMapping(path = "/api")
public class DemoController {
    final NYSECurencyService nyseCurencyService;
    final RobotCurrencyService robotCurrencyService;

    public DemoController(NYSECurencyService nyseCurencyService, RobotCurrencyService robotCurrencyService) {
        this.nyseCurencyService = nyseCurencyService;
        this.robotCurrencyService = robotCurrencyService;
    }

    @RequestMapping(path = "/test", method = RequestMethod.GET)
    public ResponseEntity<?> helloWorld(@RequestParam(name = "id", defaultValue = "GOOGLE") String id) {
        nyseCurencyService.getCurrencyServiceForStack("APPN", LocalDateTime.now());
        robotCurrencyService.getCurrencyServiceForStack("APPR", LocalDateTime.now());
        return ResponseEntity.ok("ok");
    }

    ExecutorService executorService = Executors.newFixedThreadPool(5);
    CompletionService<Float> completionService = new ExecutorCompletionService<>(executorService);

}
