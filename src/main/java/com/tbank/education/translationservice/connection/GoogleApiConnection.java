package com.tbank.education.translationservice.connection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GoogleApiConnection implements ApiConnection {

    private final RestTemplate restTemplate;
    @Value("${translate.api.url}")
    private String url;
    @Value("${translate.api.key}")
    private String apiKey;

    public GoogleApiConnection(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String translate(String text, String sourceLang, String targetLang) {
        //url = url + String.format("?key=%s&q=%s&source=%s&target=%s", apiKey, text, sourceLang, targetLang);
        //return restTemplate.getForObject(url, String.class);
        //TODO Google Api не реализован из-за невозможности настройки в Google Cloud из РФ.
        // GoogleApiConnection создан для примера масштабируемости приложения
        return "";
    }
}
