package com.spring.task.ntc_twoo.service;

import com.spring.task.ntc_twoo.model.Articles;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class NewsService extends MappingJackson2HttpMessageConverter implements NewsServiceIn {

    public NewsService() {
        setPrettyPrint(true);
    }

    public static List<Articles> categorySearch(String country, String category) throws IOException, JSONException, InvalidFormatException {

        String url = "https://newsapi.org/v2/top-headlines?apiKey=49f3c8dcde3f40978ca3c1a782bfe27f&country=" + country + "&category=" + category + "";
        return action(url);
    }

    public static List<Articles> countrySearch(String country) throws IOException, JSONException, InvalidFormatException {

        String url = "https://newsapi.org/v2/top-headlines?apiKey=49f3c8dcde3f40978ca3c1a782bfe27f&country=" + country;
        return action(url);
    }


    public static List<Articles> action(String url) throws JSONException, IOException, InvalidFormatException {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);

        List<Articles> newsList = new ArrayList<>();

        JSONObject root = new JSONObject(result);

        String name = null;
        String author = null;
        String title = null;
        String description = null;
        String urlother = null;
        String urlToImage = null;
        String publishedAt = null;

        JSONArray articlesObject = root.getJSONArray("articles");

        for (int i = 0; i < articlesObject.length(); i++) {

            JSONObject arrayElement = articlesObject.getJSONObject(i);

            JSONObject sourceother = arrayElement.getJSONObject("source");

            name = sourceother.getString("name");

            if (!arrayElement.isNull("author")) {
                author = arrayElement.getString("author");
            } else {
                author = name;
            }

            title = arrayElement.getString("title");

            if (!arrayElement.isNull("description")) {
                description = arrayElement.getString("description");
            } else {
                description = null;
            }

            urlother = arrayElement.getString("url");

            if (!arrayElement.isNull("urlToImage")) {
                urlToImage = arrayElement.getString("urlToImage");
            } else {
                urlToImage = null;
            }

            publishedAt = arrayElement.getString("publishedAt");

            Articles articles = new Articles();

            articles.setAuthor(author);
            articles.setDescription(description);
            articles.setPublishedAt(publishedAt);
            articles.setTitle(title);
            articles.setUrlSource(urlother);
            articles.setImageUrl(urlToImage);
            articles.setSource(name);
            newsList.add(articles);
        }
        SaveInfoService save = new SaveInfoService(newsList);
        return newsList;
    }
}
