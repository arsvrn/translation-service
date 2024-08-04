package com.tbank.education.translationservice.connection;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class YandexApiConnection implements ApiConnection {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${translate.api.url}")
    private String apiUrl;

    @Value("${translate.api.key}")
    private String apiKey;

    @Value("${yandex.translate.folder.id}")
    private String folderId;

    public YandexApiConnection(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.objectMapper = new ObjectMapper();
    }

    public String translate(String[] texts, String targetLang) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer " + apiKey);

        Map<String, Object> body = new HashMap<>();
        body.put("targetLanguageCode", targetLang);
        body.put("texts", texts);
        body.put("folderId", folderId);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, request, String.class);

        return extractTranslatedText(response.getBody());
    }

    @Override
    public String translate(String text, String sourceLang, String targetLang) {
        return translate(new String[]{text}, targetLang);
    }

    private String extractTranslatedText(String jsonResponse) {
        try {
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode translationsNode = rootNode.path("translations");

            StringBuilder translatedText = new StringBuilder();
            for (JsonNode translation : translationsNode) {
                String text = translation.path("text").asText();
                if (!text.isEmpty()) {
                    translatedText.append(text).append(" ");
                }
            }

            return translatedText.toString().trim();
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse JSON response", e);
        }
    }
}