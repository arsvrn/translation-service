package com.tbank.education.translationservice.repository;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Component
public class TranslationRepositoryFactory {

    @Getter
    private static TranslationRepositoryFactory instance;
    private final Map<String, Supplier<TranslationRepository>> repositoryCreators = new HashMap<>();

    @Autowired
    public TranslationRepositoryFactory(ApplicationContext applicationContext) {
        repositoryCreators.put("h2", () -> applicationContext.getBean(H2TranslationRepository.class));
        repositoryCreators.put("postgresql", () -> applicationContext.getBean(PostgreSQLTranslationRepository.class));
        instance = this;
    }

    public TranslationRepository createRepository(String dataSourceUrl) {
        String dbType = detectDatabaseType(dataSourceUrl);
        return repositoryCreators.getOrDefault(dbType, () -> {
            throw new IllegalArgumentException("Unsupported database type: " + dbType);
        }).get();
    }

    private String detectDatabaseType(String url) {
        return repositoryCreators.keySet().stream()
                .filter(url::contains)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unsupported database URL: " + url));
    }
}