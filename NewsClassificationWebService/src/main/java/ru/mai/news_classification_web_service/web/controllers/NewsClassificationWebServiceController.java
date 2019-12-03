package ru.mai.news_classification_web_service.web.controllers;

import org.springframework.web.bind.annotation.*;

import ru.mai.news_classification_web_service.web.requests.ClassificationRequest;
import ru.mai.news_classification_web_service.web.responses.ClassificationResponse;
import ru.mai.news_classification_web_service.services.classifier.NewsClassifierService;

@RestController
@RequestMapping(value = "/api")
public class NewsClassificationWebServiceController {
    private NewsClassifierService newsClassifier;

    public NewsClassificationWebServiceController() throws Exception {
        newsClassifier = new NewsClassifierService();
    }

    @PostMapping(value = "/classification")
    public ClassificationResponse classify(@RequestBody ClassificationRequest request) throws Exception {
        return new ClassificationResponse(newsClassifier.classifyText(request.getText()));
    }
}
