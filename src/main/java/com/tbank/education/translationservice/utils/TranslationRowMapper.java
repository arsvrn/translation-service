package com.tbank.education.translationservice.utils;

import com.tbank.education.translationservice.model.TranslationRecord;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TranslationRowMapper implements RowMapper<TranslationRecord> {
    @Override
    public TranslationRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
        TranslationRecord record = new TranslationRecord();
        record.setId(rs.getLong("id"));
        record.setIpAddress(rs.getString("ip_address"));
        record.setInputText(rs.getString("input_text"));
        record.setTranslatedText(rs.getString("translated_text"));
        return record;
    }
}