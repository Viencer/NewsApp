package com.spring.task.ntc_twoo.controller;


import com.spring.task.ntc_twoo.model.Group;
import com.spring.task.ntc_twoo.service.NewsService;
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

    @GetMapping(value = "/Categorizednews/{country}/{category}")
    public List<Group> sendCategorizedUpdate(@PathVariable String country, @PathVariable String category) throws ParseException, IOException, JSONException {
        return NewsService.categorySearch(country, category);
    }

    @GetMapping(value = "/Sourcednews/{country}/{source123}")
    public List<Group> sendSourcedUpdate(@PathVariable String country, @PathVariable String source123) throws ParseException, IOException, JSONException {
        return NewsService.countrySearch(country, source123);
    }

}
