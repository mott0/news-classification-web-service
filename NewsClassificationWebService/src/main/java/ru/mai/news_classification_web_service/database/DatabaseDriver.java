package ru.mai.news_classification_web_service.database;

import ru.mai.news_classification_web_service.database.entities.NewsArticle;
import ru.mai.news_classification_web_service.classifier.entities.NewsCategory;
import ru.mai.news_classification_web_service.errors.ArticlesException;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseDriver {
    public ArrayList<NewsArticle> getNewsArticles() throws ArticlesException {
        ArrayList<NewsArticle> newsArticles = new ArrayList<>();

        String sqlQuery = "SELECT YAN.Id, YAN.Title, YAN.Text, YAN.Source, NC.Name " +
                "FROM Yandex_News_Articles AS YAN " +
                "INNER JOIN NewsCategory AS NC ON YAN.NewsCategoryId = NC.Id";

        try (Connection connection = connect();
             Statement statement  = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlQuery)) {
            while (resultSet.next()) {
                newsArticles.add(new NewsArticle(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        NewsCategory.getNewsCategory(resultSet.getString(5)))
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new ArticlesException();
        }
        return newsArticles;
    }

    private Connection connect() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:data/Yandex_News_Articles.db";
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
