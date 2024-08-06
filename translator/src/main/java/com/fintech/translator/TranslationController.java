package com.fintech.translator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/translate")
public class TranslationController {

    @Autowired
    private TranslationService translationService;

    @GetMapping
    public String translate(@RequestParam String text,
                            @RequestParam String sourceLang,
                            @RequestParam String targetLang,
                            HttpServletRequest request) throws ExecutionException, InterruptedException {
        String ip = request.getRemoteAddr();
        return translationService.translateText(text, sourceLang, targetLang, ip);
    }
}
