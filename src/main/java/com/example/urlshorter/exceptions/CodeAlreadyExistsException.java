package com.example.urlshorter.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @project: UrlShorter
 * @author: Sarvar55
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class CodeAlreadyExistsException extends RuntimeException {
    public CodeAlreadyExistsException(String message) {
        super(message);
    }

}
