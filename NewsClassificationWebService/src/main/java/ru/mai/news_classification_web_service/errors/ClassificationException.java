package ru.mai.news_classification_web_service.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ClassificationException extends ResponseStatusException {
    public ClassificationException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "Произошла ошибка при классификации!");
    }
}
