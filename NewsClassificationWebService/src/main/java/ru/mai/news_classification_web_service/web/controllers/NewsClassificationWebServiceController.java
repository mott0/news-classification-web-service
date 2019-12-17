package ru.mai.news_classification_web_service.web.controllers;

import org.springframework.web.bind.annotation.*;

import ru.mai.news_classification_web_service.errors.ClassificationException;
import ru.mai.news_classification_web_service.errors.ValidationException;
import ru.mai.news_classification_web_service.services.classification.NewsClassificationService;
import ru.mai.news_classification_web_service.web.requests.ClassificationRequest;
import ru.mai.news_classification_web_service.web.responses.ClassificationResponse;

@RestController
@RequestMapping(value = "/api")
public class NewsClassificationWebServiceController {
    private NewsClassificationService newsClassificationService;

    public NewsClassificationWebServiceController() throws Exception {
        newsClassificationService = new NewsClassificationService();
    }

    @PostMapping(value = "/classification")
    public ClassificationResponse classify(@RequestBody ClassificationRequest request) throws ClassificationException, ValidationException {
        if (request.getText().trim().isEmpty()) {
            throw new ValidationException();
        }
        return new ClassificationResponse(newsClassificationService.classifyText(request.getText()));
    }
}
