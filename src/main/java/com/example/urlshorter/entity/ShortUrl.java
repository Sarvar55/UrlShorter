package com.example.urlshorter.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @project: UrlShorter
 * @author: Sarvar55
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShortUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long urlId;

    private String url;

    private String code;

}
