package ru.mai.news_classification_web_service.web.controllers;

import ru.mai.news_classification_web_service.web.requests.ClassificationRequest;
import ru.mai.news_classification_web_service.web.responses.ClassificationResponse;
import ru.mai.news_classification_web_service.classifier.NewsClassifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class NewsClassificationWebServiceController {
    private NewsClassifier newsClassifier;

    public NewsClassificationWebServiceController() throws Exception {
        newsClassifier = new NewsClassifier();
    }

    @PostMapping(value = "/classification")
    public ClassificationResponse classify(@RequestBody ClassificationRequest request) throws Exception {
        return new ClassificationResponse(newsClassifier.classifyText(request.getText()));
    }
}
