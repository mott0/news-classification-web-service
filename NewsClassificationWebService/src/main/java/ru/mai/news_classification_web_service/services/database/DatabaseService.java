package ru.mai.news_classification_web_service.services.database;

import org.springframework.stereotype.Service;

import ru.mai.news_classification_web_service.database.DatabaseDriver;
import ru.mai.news_classification_web_service.database.entities.NewsArticle;

import java.util.ArrayList;

@Service
public class DatabaseService {
    private DatabaseDriver databaseDriver;

    public DatabaseService() {
        databaseDriver = new DatabaseDriver();
    }

    public ArrayList<NewsArticle> getNewsArticles() {
        return databaseDriver.getNewsArticles();
    }
}
