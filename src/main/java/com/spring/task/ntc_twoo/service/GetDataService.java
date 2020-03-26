package com.spring.task.ntc_twoo.service;

import com.spring.task.ntc_twoo.model.Articles;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class GetDataService implements GetDataIn {

    private static final Logger logger = Logger.getLogger(GetDataService.class);

    @Override
    public Articles getData(JSONObject arrayElement) {

        String name = null;
        String author = null;
        String title = null;
        String description = null;
        String urlother = null;
        String urlToImage = null;
        String publishedAt = null;
        Articles articles = new Articles();
        try {
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

            articles.setAuthor(author);
            articles.setDescription(description);
            articles.setPublishedAt(publishedAt);
            articles.setTitle(title);
            articles.setUrlSource(urlother);
            articles.setImageUrl(urlToImage);
            articles.setSource(name);
        } catch (JSONException e) {
            logger.error(e);
        }
        return articles;
    }
}