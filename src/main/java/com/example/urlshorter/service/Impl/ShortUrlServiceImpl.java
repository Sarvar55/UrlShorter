package com.example.urlshorter.service.Impl;

import com.example.urlshorter.entity.ShortUrl;
import com.example.urlshorter.exceptions.CodeAlreadyExistsException;
import com.example.urlshorter.exceptions.ShortUrlNotFoundException;
import com.example.urlshorter.repository.ShortUrlRepository;
import com.example.urlshorter.request.ShortUrlRequest;
import com.example.urlshorter.service.ShortUrlService;
import com.example.urlshorter.util.GenerateRandomCode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

/**
 * @project: UrlShorter
 * @author: Sarvar55
 */
@Service
public class ShortUrlServiceImpl implements ShortUrlService {
    private final ShortUrlRepository repository;
    private final GenerateRandomCode generateRandomCode;

    public ShortUrlServiceImpl(ShortUrlRepository repository, GenerateRandomCode generateRandomCode) {
        this.repository = repository;
        this.generateRandomCode = generateRandomCode;
    }

    @Override
    public List<ShortUrl> getAllShortUrl() {
        return repository.findAll();
    }

    @Override
    public ShortUrl getShortUrlByCode(String code) {
        return repository.getShortUrlByCode(code).orElseThrow(
                () -> new ShortUrlNotFoundException("Code", code)
        );
    }

    @Override
    public ShortUrl getShortUrlByUrl(String url) {
        return null;
    }

    @Override
    public ShortUrl createShortUrl(ShortUrl request) {
        if (request.getCode() == null || request.getCode().isEmpty()) {
            request.setCode(generateCode());
        } else if (repository.getShortUrlByCode(request.getCode()).isPresent()) {
            throw new CodeAlreadyExistsException("code already exists");
        }
        request.setCode(request.getCode().toUpperCase(Locale.ROOT));
        return repository.save(request);
    }

    private String generateCode() {
        String code = "";
        do {
            code = generateRandomCode.generateCode();
        } while (repository.getShortUrlByCode(code).isPresent());

        return code;
    }
}
