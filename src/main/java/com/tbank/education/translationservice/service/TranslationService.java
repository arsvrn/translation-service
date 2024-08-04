package com.tbank.education.translationservice.service;

import com.tbank.education.translationservice.model.TranslationRecord;
import com.tbank.education.translationservice.model.TranslationRequest;
import com.tbank.education.translationservice.repository.TranslationRepository;
import com.tbank.education.translationservice.repository.TranslationRepositoryFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public abstract class TranslationService {

    private final ExecutorService executorService = Executors.newFixedThreadPool(10);
    @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    public String translateText(TranslationRequest request, String ipAddress) throws ExecutionException, InterruptedException {
        String[] words = request.getText().split(" ");
        List<Future<String>> futures = new ArrayList<>();

        for (String word : words) {
            futures.add(executorService.submit(() -> translateWord(word, request.getSourceLang(), request.getTargetLang())));
        }

        StringBuilder translatedText = new StringBuilder();
        for (Future<String> future : futures) {
            String translatedWord = future.get();
            if (translatedWord != null && !translatedWord.isEmpty()) {
                translatedText.append(translatedWord).append(" ");
            }
        }

        String result = translatedText.toString().trim();

        TranslationRepository repository = TranslationRepositoryFactory.getInstance().createRepository(dataSourceUrl);
        repository.saveRequest(ipAddress, request.getText(), result);

        return result;

    }

    abstract String translateWord(String word, String sourceLang, String targetLang);

    public  List<TranslationRecord> getAllRequest() {
        TranslationRepository repository = TranslationRepositoryFactory.getInstance().createRepository(dataSourceUrl);
        return repository.findAll();
    }
}
