package com.github.mott0.newsclassificationwebservice.controller.Responses;

public class SearchResponse {
    private final String result;

    public SearchResponse(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}
