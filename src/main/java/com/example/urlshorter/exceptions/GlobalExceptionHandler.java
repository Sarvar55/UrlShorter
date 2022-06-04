package com.example.urlshorter.exceptions;

import com.example.urlshorter.response.ApiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @project: UrlShorter
 * @author: Sarvar55
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        ApiResponse response = new ApiResponse();
        ex.getAllErrors().forEach(error -> {
            String message = error.getDefaultMessage();
            String fieldName = ((FieldError) error).getField();
            response.setMessage(message);
            response.setFieldName(fieldName);
            response.setSuccess(false);
        });
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(CodeAlreadyExistsException.class)
    public ResponseEntity<?> codeAlreadyExistsException(CodeAlreadyExistsException exception) {
        Map<String, String> map = new HashMap<>();
        map.put("error", exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(map);
    }

    @ExceptionHandler(ShortUrlNotFoundException.class)
    public ResponseEntity<?> shortUrlNotFoundException(ShortUrlNotFoundException exception) {
        Map<String, String> map = new HashMap<>();
        map.put("error", exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
    }

}
