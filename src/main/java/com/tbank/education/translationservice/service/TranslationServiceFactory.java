package com.tbank.education.translationservice.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Component
public class TranslationServiceFactory {

    @Getter
    private static TranslationServiceFactory instance;
    private final Map<String, Supplier<TranslationService>> serviceCreators = new HashMap<>();

    @Autowired
    public TranslationServiceFactory(ApplicationContext applicationContext) {
        serviceCreators.put("yandex", () -> applicationContext.getBean(YandexTranslationService.class));
        serviceCreators.put("google", () -> applicationContext.getBean(GoogleTranslationService.class));
        instance = this;
    }

    public TranslationService createService(String apiServiceUrl) {
        String dbType = detectDatabaseType(apiServiceUrl);
        return serviceCreators.getOrDefault(dbType, () -> {
            throw new IllegalArgumentException("Unsupported database type: " + dbType);
        }).get();
    }

    private String detectDatabaseType(String url) {
        return serviceCreators.keySet().stream()
                .filter(url::contains)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unsupported database URL: " + url));
    }
}