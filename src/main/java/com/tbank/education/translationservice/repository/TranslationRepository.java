package com.tbank.education.translationservice.repository;

import com.tbank.education.translationservice.model.TranslationRecord;
import com.tbank.education.translationservice.utils.TranslationRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public abstract class TranslationRepository {

    protected final JdbcTemplate jdbcTemplate;

    protected TranslationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public abstract void saveRequest(String ip, String inputText, String translatedText);

    public List<TranslationRecord> findAll() {
        String sql = "SELECT * FROM translations";
        return jdbcTemplate.query(sql, new TranslationRowMapper());
    }
}
