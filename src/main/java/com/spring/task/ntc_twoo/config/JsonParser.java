package com.spring.task.ntc_twoo.config;

import com.spring.task.ntc_twoo.model.Articles;
import com.spring.task.ntc_twoo.service.NewsService;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class JsonParser implements Converter<String, List<Articles>> {

    private static final Logger logger = Logger.getLogger(NewsService.class);

    private List<Articles> newsList = new ArrayList<Articles>();

    @Override
    public List<Articles> convert(String url) {

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);

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
