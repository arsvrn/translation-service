package com.tbank.education.translationservice.service;

import com.tbank.education.translationservice.connection.ApiConnection;
import com.tbank.education.translationservice.connection.GoogleApiConnection;
import org.springframework.stereotype.Service;

@Service
public class GoogleTranslationService extends TranslationService {

    private final ApiConnection apiConnection;

    public GoogleTranslationService(GoogleApiConnection apiConnection) {
        this.apiConnection = apiConnection;
    }

    @Override
    String translateWord(String word, String sourceLang, String targetLang) {
        return apiConnection.translate(word, sourceLang, targetLang);
    }
}
