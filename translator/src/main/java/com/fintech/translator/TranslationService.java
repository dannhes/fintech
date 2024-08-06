package com.fintech.translator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Service
public class TranslationService {

    @Autowired
    private TranslationRepository translationRepository;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    public String translateText(String text, String sourceLang, String targetLang, String ip) throws ExecutionException, InterruptedException {
        String[] words = text.split(" ");
        List<Future<String>> futures = new ArrayList<>();

        for (String word : words) {
            futures.add(executorService.submit(() -> translateWord(word, sourceLang, targetLang)));
        }

        StringBuilder translatedText = new StringBuilder();
        for (Future<String> future : futures) {
            translatedText.append(future.get()).append(" ");
        }

        String result = translatedText.toString().trim();
        translationRepository.saveTranslation(ip, text, result);
        return result;
    }

    private String translateWord(String word, String sourceLang, String targetLang) {
        String url = "https://translation-api-url?text=" + word + "&source=" + sourceLang + "&target=" + targetLang;
        return restTemplate.getForObject(url, String.class);
    }
}
