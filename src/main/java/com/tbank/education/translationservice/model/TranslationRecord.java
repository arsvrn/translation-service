package com.tbank.education.translationservice.model;

import lombok.Data;

@Data
public class TranslationRecord {
    private Long id;
    private String ipAddress;
    private String inputText;
    private String translatedText;
}
