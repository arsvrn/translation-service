package com.tbank.education.translationservice.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PostgreSQLTranslationRepository extends TranslationRepository {

    public PostgreSQLTranslationRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public void saveRequest(String ipAddress, String originalText, String translatedText) {
        String sql = "INSERT INTO translations (ip_address, input_text, translated_text) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, ipAddress, originalText, translatedText);
    }
}
