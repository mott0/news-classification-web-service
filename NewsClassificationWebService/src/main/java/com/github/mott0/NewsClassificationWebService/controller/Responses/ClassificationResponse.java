package com.github.mott0.NewsClassificationWebService.controller.Responses;

import com.github.mott0.NewsClassifier.NewsCategory;

public class ClassificationResponse {
    private NewsCategory newsCategory;

    public ClassificationResponse(NewsCategory newsCategory) {
        this.newsCategory = newsCategory;
    }

    public NewsCategory getNewsCategory() {
        return newsCategory;
    }
}
