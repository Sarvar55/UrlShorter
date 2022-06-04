package com.example.urlshorter.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @project: UrlShorter
 * @author: Sarvar55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private String message;
    private String fieldName;
    private boolean success;
}
