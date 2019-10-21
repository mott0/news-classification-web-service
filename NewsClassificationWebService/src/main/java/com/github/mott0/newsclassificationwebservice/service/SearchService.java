package com.github.mott0.newsclassificationwebservice.service;

import com.github.mott0.newsclassificationwebservice.controller.Responses.SearchResponse;

public class SearchService {
    public static SearchResponse getLength(String text) {
        return new SearchResponse("Your length is " + String.valueOf(text.length()));
    }
}
