package com.spring.task.ntc_twoo.controller;

import com.spring.task.ntc_twoo.model.Articles;
import com.spring.task.ntc_twoo.service.NewsServiceIn;
import com.spring.task.ntc_twoo.service.SaveInfoService;
import com.spring.task.ntc_twoo.service.SaveInfoServiceIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
public class NewsController {

    @Autowired
    NewsServiceIn newsServiceIn;
    SaveInfoServiceIn saveInfoService;

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
    public ResponseEntity sendSourcedUpdateDefault(@PathVariable String country) {
        List<Articles> articles = newsServiceIn.countrySearch(country);
        return new ResponseEntity<List>(articles, HttpStatus.OK);
    }

    @GetMapping(value = "/country/{country}/word")
    public ResponseEntity<FileOutputStream> wordSaveCountry(@PathVariable String country) throws IOException {
        SaveInfoService service = new SaveInfoService();
        FileOutputStream outputStream = new FileOutputStream("dod.docx");
        service.saveCountry(country).writeTo(outputStream);
        return ResponseEntity.ok().body(outputStream);
    }

    @GetMapping(value = "/category/{country}/{category}/word")
    public File wordSaveCategory(@PathVariable String country, @PathVariable String category) throws IOException {
        saveInfoService.saveCategory(country, category);
        return new File("ss.docx");
    }
}
