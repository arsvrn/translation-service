package com.tbank.education.translationservice;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Objects;

@ExtendWith(SpringExtension.class)
public abstract class TranslationApplicationTest {

    @BeforeAll
    public static void setup() {
        Dotenv dotenv = Dotenv.load();
        System.setProperty("DB_DRIVER", Objects.requireNonNull(dotenv.get("DB_DRIVER")));
        System.setProperty("DB_URL", Objects.requireNonNull(dotenv.get("DB_URL")));
        System.setProperty("DB_USERNAME", Objects.requireNonNull(dotenv.get("DB_USERNAME")));
        System.setProperty("DB_PASSWORD", Objects.requireNonNull(dotenv.get("DB_PASSWORD")));
        System.setProperty("TRANSLATE_API_URL", Objects.requireNonNull(dotenv.get("TRANSLATE_API_URL")));
        System.setProperty("TRANSLATE_API_KEY", Objects.requireNonNull(dotenv.get("TRANSLATE_API_KEY")));
        System.setProperty("TRANSLATE_FOLDER_API", Objects.requireNonNull(dotenv.get("TRANSLATE_FOLDER_API")));
    }
}
