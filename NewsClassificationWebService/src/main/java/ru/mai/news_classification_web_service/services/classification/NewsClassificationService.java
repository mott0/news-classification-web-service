package ru.mai.news_classification_web_service.services.classification;

import org.springframework.stereotype.Service;
import ru.mai.news_classification_web_service.classifier.NewsClassifier;
import ru.mai.news_classification_web_service.classifier.entities.NewsCategory;

@Service
public class NewsClassificationService {
    private NewsClassifier newsClassifier;

    public NewsClassificationService() throws Exception { newsClassifier = new NewsClassifier(); }

    public NewsCategory classifyText(String text) throws Exception { return newsClassifier.classifyText(text); }
}
