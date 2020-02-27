package com.spring.task.ntc_twoo.controller;


import com.spring.task.ntc_twoo.model.Articles;
import com.spring.task.ntc_twoo.service.NewsService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.JSONException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
public class NewsController {

    @RequestMapping(value = "//", method = RequestMethod.GET)
    public String usage_guide() {
        return " News rest api";
    }

    @GetMapping(value = "/category/{country}/{category}")
    public List<Articles> sendCategorizedUpdate(@PathVariable String country, @PathVariable String category) throws ParseException, IOException, JSONException, InvalidFormatException {
        return NewsService.categorySearch(country, category);
    }

    @GetMapping(value = "/country/{country}")
    public List<Articles> sendSourcedUpdate(@PathVariable String country) throws ParseException, IOException, JSONException, InvalidFormatException {
        return NewsService.countrySearch(country);
    }

}
