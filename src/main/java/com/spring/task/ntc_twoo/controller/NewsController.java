package com.spring.task.ntc_twoo.controller;

import com.spring.task.ntc_twoo.model.Articles;
import com.spring.task.ntc_twoo.service.NewsService;
import com.spring.task.ntc_twoo.service.NewsServiceIn;

import com.spring.task.ntc_twoo.service.SaveInfoService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
public class NewsController {

    @Autowired
    NewsServiceIn newsServiceIn;

    @RequestMapping(value = "//", method = RequestMethod.GET)
    public String usage_guide() {
        return "поиск по категориям /category/country/category(.json или .xml) без скобок__________" +
                "поиск по странам /country/country(.json или .xml) без скобок";
    }

    @GetMapping(value = "/category/{country}/{category}")
    public ResponseEntity sendCategorizedUpdateDefault(@PathVariable String country, @PathVariable String category) {
        List<Articles> articles = newsServiceIn.categorySearch(country, category);
        return new ResponseEntity<List>(articles, HttpStatus.OK);
    }


    @GetMapping(value = "/country/{country}")
    public ResponseEntity sendSourcedUpdateDefault(@PathVariable String country) throws JSONException {
        List<Articles> articles = newsServiceIn.countrySearch(country);
        return new ResponseEntity<List>(articles, HttpStatus.OK);
    }

    @GetMapping(value = "/country/{country}/word")
    public String wordSaveCountry(@PathVariable String country) {
        SaveInfoService.saveCountry(country);
        return "Информация сохранена";
    }

    @GetMapping(value = "/category/{country}/{category}/word")
    public String wordSaveCategory(@PathVariable String country, @PathVariable String category) {
        SaveInfoService.saveCategory(country, category);
        return "Информация сохранена";
    }
}
