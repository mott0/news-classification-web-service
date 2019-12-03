package ru.mai.news_classification_web_service.web.responses;

import ru.mai.news_classification_web_service.services.classifier.NewsCategory;

public class ClassificationResponse {
    private NewsCategory newsCategory;

    public ClassificationResponse(NewsCategory newsCategory) {
        this.newsCategory = newsCategory;
    }

    public NewsCategory getNewsCategory() {
        return newsCategory;
    }
}
