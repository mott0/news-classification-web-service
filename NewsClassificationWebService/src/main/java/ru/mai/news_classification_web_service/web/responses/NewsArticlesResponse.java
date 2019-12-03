package ru.mai.news_classification_web_service.web.responses;

import ru.mai.news_classification_web_service.database.entities.NewsArticle;

import java.util.ArrayList;

public class NewsArticlesResponse {
    private ArrayList<NewsArticle> newsArticles;

    public NewsArticlesResponse(ArrayList<NewsArticle> newsArticles) {
        this.newsArticles = newsArticles;
    }

    public ArrayList<NewsArticle> getNewsArticles() {
        return newsArticles;
    }
}
