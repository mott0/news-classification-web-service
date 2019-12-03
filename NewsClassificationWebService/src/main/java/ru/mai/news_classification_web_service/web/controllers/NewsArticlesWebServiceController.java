package ru.mai.news_classification_web_service.web.controllers;

import org.springframework.web.bind.annotation.*;

import ru.mai.news_classification_web_service.services.database.DatabaseService;
import ru.mai.news_classification_web_service.web.responses.NewsArticlesResponse;

@RestController
@RequestMapping(value = "/api")
public class NewsArticlesWebServiceController {
    private DatabaseService databaseService;

    public NewsArticlesWebServiceController() {
        databaseService = new DatabaseService();
    }

    @GetMapping(value = "/newsArticles")
    public NewsArticlesResponse getNewsArticles() {
        return new NewsArticlesResponse(databaseService.getNewsArticles());
    }
}
