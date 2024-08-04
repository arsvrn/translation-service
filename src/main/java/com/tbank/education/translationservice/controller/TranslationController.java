package com.tbank.education.translationservice.controller;

import com.tbank.education.translationservice.model.TranslationRecord;
import com.tbank.education.translationservice.model.TranslationRequest;
import com.tbank.education.translationservice.service.TranslationService;
import com.tbank.education.translationservice.service.TranslationServiceFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/translate")
@Slf4j
@Tag(name = "Translation Controller", description = "Контроллер для перевода")
public class TranslationController {

    @Value("${translate.api.url}")
    private String apiUrl;

    @Operation(summary = "Перевод текста")
    @PostMapping
    public ResponseEntity<String> translate(@RequestBody TranslationRequest request,
                                            HttpServletRequest httpRequest) {
        try {
            String userIp = httpRequest.getRemoteAddr();
            TranslationService translationService = TranslationServiceFactory.getInstance().createService(apiUrl);

            String translatedText = translationService.translateText(request, userIp);
            return ResponseEntity.ok(translatedText);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @Operation(summary = "Получить все запросы")
    @GetMapping
    public ResponseEntity<List<TranslationRecord>> getAllRequest () {
        TranslationService translationService = TranslationServiceFactory.getInstance().createService(apiUrl);
        return ResponseEntity.ok(translationService.getAllRequest());
    }
}