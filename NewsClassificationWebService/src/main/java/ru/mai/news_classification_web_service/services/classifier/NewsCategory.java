package ru.mai.news_classification_web_service.services.classifier;

public enum NewsCategory {
    CULTURE("culture"),
    ECONOMICS("economics"),
    INCIDENTS("incidents"),
    POLITICS("politics"),
    SCIENCE("science"),
    SOCIETY("society"),
    TECHNOLOGIES("technologies"),
    TRANSPORT("transport");

    private String category;

    NewsCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return this.category;
    }

    public static NewsCategory getNewsCategory(String category) {
        try {
            return NewsCategory.valueOf(category);
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }
}
