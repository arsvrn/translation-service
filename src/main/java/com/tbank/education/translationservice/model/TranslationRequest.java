package com.tbank.education.translationservice.model;

import lombok.Data;

@Data
public class TranslationRequest {
    private String sourceLang;
    private String targetLang;
    private String text;
}
