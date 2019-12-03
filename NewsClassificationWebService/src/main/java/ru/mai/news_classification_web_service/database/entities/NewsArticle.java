package ru.mai.news_classification_web_service.database.entities;

import ru.mai.news_classification_web_service.services.classifier.NewsCategory;

public class NewsArticle {
    private int id;
    private String title;
    private String text;
    private String source;
    private NewsCategory newsCategory;

    public NewsArticle(int id, String title, String text, String source, NewsCategory newsCategory) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.source = source;
        this.newsCategory = newsCategory;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getSource() {
        return source;
    }

    public NewsCategory getNewsCategory() {
        return newsCategory;
    }
}
