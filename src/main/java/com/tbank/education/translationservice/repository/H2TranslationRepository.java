package com.tbank.education.translationservice.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class H2TranslationRepository extends TranslationRepository {


    public H2TranslationRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public void saveRequest(String ipAddress, String originalText, String translatedText) {
        String sql = "INSERT INTO translations (ip_address, input_text, translated_text) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, ipAddress, originalText, translatedText);
    }
}
