package com.github.mott0.NewsClassificationWebService.controller;

import com.github.mott0.NewsClassificationWebService.controller.Requests.ClassificationRequest;
import com.github.mott0.NewsClassificationWebService.controller.Responses.ClassificationResponse;
import com.github.mott0.NewsClassifier.NewsClassifier;
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
