package com.tbank.education.translationservice;

import com.tbank.education.translationservice.repository.H2TranslationRepository;
import com.tbank.education.translationservice.repository.PostgreSQLTranslationRepository;
import com.tbank.education.translationservice.repository.TranslationRepository;
import com.tbank.education.translationservice.repository.TranslationRepositoryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TranslationRepositoryFactoryTest extends TranslationApplicationTest {

    @Autowired
    private TranslationRepositoryFactory factory;

    @Test
    public void testCreateH2Repository() {
        String h2Url = "jdbc:h2://localhost:5432/test";

        TranslationRepository repository = factory.createRepository(h2Url);

        assertNotNull(repository, "Repository should not be null");
        assertTrue(repository instanceof H2TranslationRepository, "Repository should be an instance of H2TranslationRepository");
    }

    @Test
    public void testCreatePostgresRepository() {
        String postgresUrl = "jdbc:postgresql://localhost:5432/test";
        TranslationRepository repository = factory.createRepository(postgresUrl);

        assertNotNull(repository, "Repository should not be null");
        assertTrue(repository instanceof PostgreSQLTranslationRepository, "Repository should be an instance of PostgreSQLTranslationRepository");
    }

    @Test
    public void testCreateRepositoryUnsupportedDatabase() {
        String unsupportedUrl = "jdbc:unsupported://";

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> factory.createRepository(unsupportedUrl));

        assertEquals("Unsupported database URL: " + unsupportedUrl, thrown.getMessage());
    }
}