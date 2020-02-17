package com.spring.task.ntc_twoo.service;

import com.spring.task.ntc_twoo.model.Articles;
import com.spring.task.ntc_twoo.model.Group;
import com.spring.task.ntc_twoo.model.Source;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class NewsService implements NewsServiseIn {


    public static List<Group> categorySearch(String country, String category) throws IOException, JSONException {

        String urlString = "https://newsapi.org/v2/top-headlines?apiKey=a24d98f562554d239d33c2f9d3da0983&country=" + country + "&category=" + category + "";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(urlString, String.class);

        JSONObject root = new JSONObject(result);

        String status = null;
        Integer totalResults = null;
        String name = null;
        String author = null;
        String title = null;
        String description = null;
        String urlother = null;
        String urlToImage = null;
        String publishedAt = null;

        List<Group> newsList = new ArrayList<>();

        status = root.getString("status");
        totalResults = root.getInt("totalResults");

        JSONArray articlesObject = root.getJSONArray("articles");

        for (int i = 0; i < articlesObject.length(); i++) {

            JSONObject arrayElement = articlesObject.getJSONObject(i);

            JSONObject sourceother = arrayElement.getJSONObject("source");

            name = sourceother.getString("name");

            if (!arrayElement.isNull("author")) {
                author = arrayElement.getString("author");
            } else {
                author = null;
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


            Group emp = new Group();
            Articles articles = new Articles();
            Source source = new Source();

            emp.setStatus(status);
            emp.setTotal(totalResults);
            articles.setAuthor(author);
            articles.setDescription(description);
            articles.setPublishedAt(publishedAt);
            articles.setTitle(title);
            articles.setUrlSource(urlother);
            articles.setImageUrl(urlToImage);
            source.setName(name);

            articles.setSource(source);
            emp.setArticles(articles);
            newsList.add(emp);
        }
        return newsList;

    }

    public static List<Group> countrySearch(String country, String source) throws IOException, JSONException {

        String urlString = "https://newsapi.org/v2/top-headlines?apiKey=a24d98f562554d239d33c2f9d3da0983&country=" + country + "&source=" + source + "";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(urlString, String.class);

        JSONObject root = new JSONObject(result);
        String status = null;
        Integer totalResults = null;
        String name = null;
        String author = null;
        String title = null;
        String description = null;
        String urlother = null;
        String urlToImage = null;
        String publishedAt = null;

        List<Group> newsList = new ArrayList<>();

        status = root.getString("status");
        totalResults = root.getInt("totalResults");

        JSONArray articlesObject = root.getJSONArray("articles");

        for (int i = 0; i < articlesObject.length(); i++) {

            JSONObject arrayElement = articlesObject.getJSONObject(i);

            JSONObject sourceother = arrayElement.getJSONObject("source");


            name = sourceother.getString("name");

            if (!arrayElement.isNull("author")) {
                author = arrayElement.getString("author");
            } else {
                author = null;
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

            Group emp = new Group();
            Articles articles = new Articles();
            Source source1 = new Source();

            emp.setStatus(status);
            emp.setTotal(totalResults);
            articles.setAuthor(author);
            articles.setDescription(description);
            articles.setPublishedAt(publishedAt);
            articles.setTitle(title);
            articles.setUrlSource(urlother);
            articles.setImageUrl(urlToImage);
            source1.setName(name);

            articles.setSource(source1);
            emp.setArticles(articles);
            newsList.add(emp);
        }
        return newsList;
    }

}