package com.spring.task.ntc_twoo.service;

import com.spring.task.ntc_twoo.model.Articles;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsService implements NewsServiceIn {

    private static final Logger logger = Logger.getLogger(NewsService.class);

    private final UriComponentsBuilder urlSite;

    public NewsService(@Value("${apiKey}") String key) {
        this.urlSite = UriComponentsBuilder.fromHttpUrl("https://newsapi.org/v2/top-headlines").queryParam("apiKey", key);
    }

    public List<Articles> categorySearch(String country, String category) {
        String url = urlSite.cloneBuilder().queryParam("country", country).queryParam("category", category).build().toString();
        return action(url);
    }

    public List<Articles> countrySearch(String country) {
        String url = urlSite.cloneBuilder().queryParam("country", country).build().toString();
        return action(url);
    }

    private List<Articles> action(String url) {

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);

        List<Articles> newsList = new ArrayList<Articles>();
        String name = null;
        String author = null;
        String title = null;
        String description = null;
        String urlother = null;
        String urlToImage = null;
        String publishedAt = null;

        try {
            JSONObject root = new JSONObject(result);
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

                description = arrayElement.getString("description");

                urlother = arrayElement.getString("url");

                urlToImage = arrayElement.getString("urlToImage");

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
        } catch (JSONException e) {
            logger.error(e);
        }
        return newsList;
    }
}
