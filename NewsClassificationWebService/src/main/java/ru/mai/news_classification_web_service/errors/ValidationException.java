package ru.mai.news_classification_web_service.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ValidationException extends ResponseStatusException {
    public ValidationException() {
        super(HttpStatus.BAD_REQUEST, "Некорректные входные данные!");
    }
}
