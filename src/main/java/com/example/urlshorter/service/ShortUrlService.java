package com.example.urlshorter.service;

import com.example.urlshorter.entity.ShortUrl;
import com.example.urlshorter.request.ShortUrlRequest;

import java.util.List;

/**
 * @project: UrlShorter
 * @author: Sarvar55
 */
public interface ShortUrlService {

    public List<ShortUrl> getAllShortUrl();

    public ShortUrl getShortUrlByCode(String code);

    public ShortUrl getShortUrlByUrl(String url);

    public ShortUrl createShortUrl(ShortUrl shortUrl);

}
