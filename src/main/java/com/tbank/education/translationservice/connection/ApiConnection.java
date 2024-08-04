package com.tbank.education.translationservice.connection;

import org.springframework.stereotype.Component;

@Component
public interface ApiConnection {
    String translate(String text, String sourceLang, String targetLang);
}
