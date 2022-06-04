package com.example.urlshorter.controller;

import com.example.urlshorter.dto.ShortUrlDto;
import com.example.urlshorter.entity.ShortUrl;
import com.example.urlshorter.request.ShortUrlRequest;
import com.example.urlshorter.service.ShortUrlService;
import lombok.Getter;
import lombok.SneakyThrows;
import org.hibernate.validator.constraints.URL;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @project: UrlShorter
 * @author: Sarvar55
 */
@RestController
@RequestMapping("/api/v1")
public class ShortUrlController {

    private final ModelMapper modelMapper;
    private final ShortUrlService service;

    public ShortUrlController(ModelMapper modelMapper, ShortUrlService service) {
        this.modelMapper = modelMapper;
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ShortUrlDto>> getAllUrl() {
        return ResponseEntity.ok(
                service.getAllShortUrl()
                        .stream()
                        .map(shortUrl -> modelMapper.map(shortUrl, ShortUrlDto.class))
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/getCode/{code}")
    public ResponseEntity<ShortUrlDto> getUrlByCode(@Valid @PathVariable String code) {
        return ResponseEntity.ok(modelMapper.map(service.getShortUrlByCode(code), ShortUrlDto.class));
    }

    @SneakyThrows
    @GetMapping("/{code}")
    public ResponseEntity<ShortUrlDto> redirect(@Valid @PathVariable String code) {
        ShortUrl shortUrl = service.getShortUrlByCode(code);
        URI uri = new URI(shortUrl.getUrl());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uri);
        return ResponseEntity.status(HttpStatus.SEE_OTHER).headers(httpHeaders).build();
    }

    @PostMapping("/create")
    public ResponseEntity<ShortUrlDto> createShortUrl(@Valid @RequestBody ShortUrlRequest request) {
        ShortUrl shortUrl = service.createShortUrl(modelMapper.map(request, ShortUrl.class));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{code}")
                .buildAndExpand(shortUrl.getCode())
                .toUri();
        return ResponseEntity.created(location).body(modelMapper.map(shortUrl, ShortUrlDto.class));
    }
}
