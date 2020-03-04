package com.spring.task.ntc_twoo.controller;

import com.spring.task.ntc_twoo.model.Articles;
import com.spring.task.ntc_twoo.service.NewsService;
import com.spring.task.ntc_twoo.service.SaveInfoService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.JSONException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class NewsController {

    @RequestMapping(value = "//", method = RequestMethod.GET)
    public String usage_guide() {
        return "поиск по категориям /category/country/category/(json или xml) без скобок__________" +
                "поиск по странам /country/country/(json или xml) без скобок";
    }

    @GetMapping(value = "/category/{country}/{category}/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public List<Articles> sendCategorizedUpdateXml(@PathVariable String country, @PathVariable String category) throws JSONException {
        return NewsService.categorySearch(country, category);
    }

    @GetMapping(value = "/category/{country}/{category}/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Articles> sendCategorizedUpdateJson(@PathVariable String country, @PathVariable String category) throws JSONException {
        return NewsService.categorySearch(country, category);
    }

    @GetMapping(value = "/category/{country}/{category}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Articles> sendCategorizedUpdateDefault(@PathVariable String country, @PathVariable String category) throws JSONException {
        return NewsService.categorySearch(country, category);
    }

    @GetMapping(value = "/country/{country}/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public List<Articles> sendSourcedUpdateXml(@PathVariable String country) throws JSONException {
        return NewsService.countrySearch(country);
    }

    @GetMapping(value = "/country/{country}/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Articles> sendSourcedUpdateJson(@PathVariable String country) throws JSONException {
        return NewsService.countrySearch(country);
    }

    @GetMapping(value = "/country/{country}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Articles> sendSourcedUpdateDefault(@PathVariable String country) throws JSONException {
        return NewsService.countrySearch(country);
    }

//    @GetMapping(value = "/country/{country}/word")
//    public ResponseEntity wordSaveCountry(@PathVariable String country) throws IOException, InvalidFormatException, JSONException {
//        //SaveInfoService.saveCountry(country);
//        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "axf.docx").body(new FileOutputStream(new File("Save.docx")));
//    }

    @GetMapping(value = "/category/{country}/{category}/word")
    public String wordSaveCategory(@PathVariable String country, @PathVariable String category) throws IOException, InvalidFormatException, JSONException {
        SaveInfoService.saveCategory(country, category);
        return "Информация сохранена";
    }
}
