package com.example.urlshorter.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * @project: UrlShorter
 * @author: Sarvar55
 */
@Component
public class GenerateRandomCode {

    @Value("${codeLength}")
    private int codeLength;

    public String generateCode() {
        StringBuilder builder = new StringBuilder();
        SecureRandom random = new SecureRandom();
        var letters = "qwertyuıWplkjh45gfdsazmxcnbm789456123"
                .chars()
                .mapToObj(value -> (char) value)
                .collect(Collectors.toList());

        Collections.shuffle(letters);
        for (int i = 0; i < codeLength; i++) {
            builder.append(letters.get(random.nextInt(letters.size())));
        }
        return builder.toString();
    }

}
