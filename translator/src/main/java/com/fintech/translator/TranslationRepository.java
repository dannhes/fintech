package com.fintech.translator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TranslationRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveTranslation(String ip, String inputText, String translatedText) {
        String sql = "INSERT INTO translations (ip, input_text, translated_text) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, ip, inputText, translatedText);
    }
}
