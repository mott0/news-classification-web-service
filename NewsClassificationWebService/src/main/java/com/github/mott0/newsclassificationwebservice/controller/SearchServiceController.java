package com.github.mott0.newsclassificationwebservice.controller;

import com.github.mott0.newsclassificationwebservice.controller.Requests.SearchRequest;
import com.github.mott0.newsclassificationwebservice.controller.Responses.SearchResponse;
import com.github.mott0.newsclassificationwebservice.service.SearchService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class SearchServiceController {
    @PostMapping(value = "/search")
    public SearchResponse search(@RequestBody SearchRequest request) {
        return SearchService.getLength(request.getText());
    }
}
