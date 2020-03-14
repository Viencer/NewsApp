package com.spring.task.ntc_twoo.service;

import com.spring.task.ntc_twoo.model.Articles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class NewsService implements NewsServiceIn {

    private final UriComponentsBuilder urlSite;

    @Autowired
    private ConversionService conversionService;

//    @Autowired
//    private JsonParser jsonParser;

    public NewsService(@Value("${apiKey}") String key) {
        this.urlSite = UriComponentsBuilder.fromHttpUrl("https://newsapi.org/v2/top-headlines").queryParam("apiKey", key);
    }

    public List<Articles> categorySearch(String country, String category) {
        String url = urlSite.cloneBuilder().queryParam("country", country).queryParam("category", category).build().toString();
//        return jsonParser.convert(url);
        return conversionService.convert(url, List.class);
    }

    public List<Articles> countrySearch(String country) {
        String url = urlSite.cloneBuilder().queryParam("country", country).build().toString();
//        return jsonParser.convert(url);
        return conversionService.convert(url, List.class);
    }
}
