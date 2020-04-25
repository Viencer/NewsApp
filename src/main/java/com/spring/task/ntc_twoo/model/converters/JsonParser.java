package com.spring.task.ntc_twoo.model.converters;

import com.spring.task.ntc_twoo.model.Articles;
import com.spring.task.ntc_twoo.service.GetDataIn;
import com.spring.task.ntc_twoo.service.GetDataService;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class JsonParser implements Converter<String, List<Articles>> {

    private static final Logger logger = Logger.getLogger(JsonParser.class);

    @Override
    public List<Articles> convert(String url) {
        List<Articles> newsList = new ArrayList<Articles>();
        GetDataIn getDataIn = new GetDataService();
        try {
            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(url, String.class);
            JSONObject root = new JSONObject(result);
            JSONArray articlesObject = root.getJSONArray("articles");

            for (int i = 0; i < articlesObject.length(); i++) {
                JSONObject arrayElement = articlesObject.getJSONObject(i);
                newsList.add(getDataIn.getData(arrayElement));
            }
        } catch (JSONException e) {
            logger.error(e);
        }
        return newsList;
    }
}
