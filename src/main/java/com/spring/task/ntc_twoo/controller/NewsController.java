package com.spring.task.ntc_twoo.controller;

import com.spring.task.ntc_twoo.model.Articles;
import com.spring.task.ntc_twoo.service.NewsService;
import com.spring.task.ntc_twoo.service.SaveInfoService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.JSONException;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@RestController
public class NewsController {

    @RequestMapping(value = "//", method = RequestMethod.GET)
    public String usage_guide() {
        return "поиск по категориям /category/{country}/{category} " +
                "поиск по странам /country/{country}";
    }

    @GetMapping(value = "/category/{country}/{category}")
    public List<Articles> sendCategorizedUpdate(@PathVariable String country, @PathVariable String category) throws IOException, JSONException, InvalidFormatException {
        return NewsService.categorySearch(country, category);
    }

    @GetMapping(value = "/country/{country}")
    public List<Articles> sendSourcedUpdate(@PathVariable String country) throws IOException, JSONException, InvalidFormatException {
        return NewsService.countrySearch(country);
    }

    @GetMapping(value = "/country/{country}/word")
    public String wordSaveCountry(@PathVariable String country) throws IOException, InvalidFormatException {
        SaveInfoService.save();
        return "Информация сохранена";
    }

    @GetMapping(value = "/category/{country}/{category}/word")
    public String wordSaveCategory(@PathVariable String country, @PathVariable String category) throws IOException, InvalidFormatException {
        SaveInfoService.save();
        return "Информация сохранена";
    }

    @GetMapping(value = "/word")
    public String wordSaveSession() throws IOException, InvalidFormatException {
        SaveInfoService.save();
        return "Информация сохранена";
    }

}
