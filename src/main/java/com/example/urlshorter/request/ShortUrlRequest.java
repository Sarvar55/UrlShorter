package com.example.urlshorter.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @project: UrlShorter
 * @author: Sarvar55
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShortUrlRequest {
    @NotEmpty
    @NotNull
    private String url;

    private String code;
}
