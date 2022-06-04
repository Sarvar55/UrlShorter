package com.example.urlshorter.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @project: UrlShorter
 * @author: Sarvar55
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
@Getter
@Setter
public class ShortUrlNotFoundException extends RuntimeException {
    private String fieldName;
    private String fieldValue;

    public ShortUrlNotFoundException(String fieldName, String fieldValue) {
        super(String.format("%s not found with %s", fieldName, fieldValue));
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
