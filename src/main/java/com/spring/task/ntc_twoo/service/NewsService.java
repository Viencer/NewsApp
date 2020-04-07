package com.spring.task.ntc_twoo.service;

import com.spring.task.ntc_twoo.model.Articles;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.concurrent.*;

@Service
public class NewsService implements NewsServiceIn {

    private static final Logger logger = Logger.getLogger(NewsService.class);

    private final UriComponentsBuilder urlSite;

    private final int thread;

    @Autowired
    private ConversionService conversionService;

    public NewsService(@Value("${apiKey}") String key, @Value("${thread}") int thread) {
        this.urlSite = UriComponentsBuilder.fromHttpUrl("https://newsapi.org/v2/top-headlines").queryParam("apiKey", key);
        this.thread = thread;
    }

    public List<Articles> categorySearch(String country, String category) throws ExecutionException, InterruptedException {
        String url = urlSite.cloneBuilder().queryParam("country", country).queryParam("category", category).build().toString();
        try {
            return async(url);
        } catch (ExecutionException | InterruptedException e) {
            logger.error("async error " + e);
        }
        return async(url);
    }

    public List<Articles> countrySearch(String country) throws ExecutionException, InterruptedException {
        String url = urlSite.cloneBuilder().queryParam("country", country).build().toString();
        try {
            return async(url);
        } catch (ExecutionException | InterruptedException e) {
            logger.error("async error " + e);
        }
        return async(url);
    }

    private List<Articles> async(String url) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(thread);
        CompletionService<List<Articles>> completionService = new ExecutorCompletionService<List<Articles>>(executorService);
        Future<List<Articles>> future;
        future = completionService.submit(() -> conversionService.convert(url, List.class));
        return future.get();
    }
}
