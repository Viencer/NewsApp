package com.spring.task.ntc_twoo.service;

import com.spring.task.ntc_twoo.model.Articles;

import java.util.List;

public interface NewsServiceIn {

    List<Articles> categorySearch(String country, String category);

    List<Articles> countrySearch(String country);
}
