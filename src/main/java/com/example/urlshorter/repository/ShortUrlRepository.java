package com.example.urlshorter.repository;

import com.example.urlshorter.entity.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @project: UrlShorter
 * @author: Sarvar55
 */
public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {
    Optional<ShortUrl> getShortUrlByCode(String code);
    Optional<ShortUrl> getShortUrlsByUrl(String url);
}
