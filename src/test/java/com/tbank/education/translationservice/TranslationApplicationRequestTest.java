package com.tbank.education.translationservice;

import com.tbank.education.translationservice.model.TranslationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TranslationApplicationRequestTest extends TranslationApplicationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testTranslate() {
        TranslationRequest request = new TranslationRequest();
        request.setText("Hello world");
        request.setSourceLang("en");
        request.setTargetLang("ru");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<TranslationRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<String> response = restTemplate.postForEntity("/api/translate", entity, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("Привет мир");
    }
}
