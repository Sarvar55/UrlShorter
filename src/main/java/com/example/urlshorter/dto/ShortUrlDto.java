package com.example.urlshorter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @project: UrlShorter
 * @author: Sarvar55
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShortUrlDto {
    private long urlId;

    private String url;

    private String code;
}
