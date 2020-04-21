package com.spring.task.ntc_twoo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@Controller
public class NewsController {

//    @Autowired
//    private NewsServiceIn newsServiceIn;

    @GetMapping("//")
    public String usage(String name, Model model) {
        model.addAttribute("name", name);
        return "welcome";
    }

//    @GetMapping(value = "/category")
//    public ProcesorR sendCategorizedUpdateDefault(@PathVariable String country, @PathVariable String category) throws ExecutionException, InterruptedException {
//        List<Articles> articles = newsServiceIn.categorySearch(country, category);
//        return new ResponseEntity<List<Articles>>(articles, HttpStatus.OK);
//    }


//    @GetMapping(value = "/country/{country}")
//    public ResponseEntity<List<Articles>> sendSourcedUpdateDefault(@PathVariable String country) throws ExecutionException, InterruptedException {
//        List<Articles> articles = newsServiceIn.countrySearch(country);
//        return new ResponseEntity<List<Articles>>(articles, HttpStatus.OK);
//    }
}
