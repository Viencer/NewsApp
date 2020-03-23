package com.spring.task.ntc_twoo.controller;

import com.spring.task.ntc_twoo.model.Articles;
import com.spring.task.ntc_twoo.service.NewsServiceIn;
import com.spring.task.ntc_twoo.service.SaveInfoServiceIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class NewsController {

    @Autowired
    private NewsServiceIn newsServiceIn;
    @Autowired
    private SaveInfoServiceIn saveInfoService;

    @RequestMapping(value = "//", method = RequestMethod.GET)
    public String usage_guide() {
        return "поиск по категориям /category/country/category(.json или .xml) без скобок__________" +
                "поиск по странам /country/country(.json или .xml) без скобок";
    }

    @GetMapping(value = "/category/{country}/{category}")
    public ResponseEntity<List<Articles>> sendCategorizedUpdateDefault(@PathVariable String country, @PathVariable String category) {
        List<Articles> articles = newsServiceIn.categorySearch(country, category);
        return new ResponseEntity<List<Articles>>(articles, HttpStatus.OK);
    }


    @GetMapping(value = "/country/{country}")
    public ResponseEntity<List<Articles>> sendSourcedUpdateDefault(@PathVariable String country) {
        List<Articles> articles = newsServiceIn.countrySearch(country);
        return new ResponseEntity<List<Articles>>(articles, HttpStatus.OK);
    }

    @GetMapping(value = "/country/{country}/word")
    public ResponseEntity<byte[]> wordSaveCountry(@PathVariable String country) {
        File file = new File("Country_Save.docx");
        byte[] doc = saveInfoService.saveCountry(country).toByteArray();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                .contentLength(doc.length)
                .body(doc);
    }

    @GetMapping(value = "/category/{country}/{category}/word")
    public ResponseEntity<byte[]> wordSaveCategory(@PathVariable String country, @PathVariable String category) throws IOException {
        File file = new File("Category_Save.docx");
        byte[] doc = saveInfoService.saveCategory(country, category).toByteArray();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                .contentLength(doc.length)
                .body(doc);
    }
}
