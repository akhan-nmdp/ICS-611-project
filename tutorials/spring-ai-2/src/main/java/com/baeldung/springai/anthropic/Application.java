package com.baeldung.springai.anthropic;

import org.springframework.ai.autoconfigure.openai.OpenAiAutoConfiguration;
import org.springframework.ai.autoconfigure.vectorstore.chroma.ChromaVectorStoreAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * Excluding the below auto-configurations to avoid start up
 * failure. Their corresponding starters are present on the classpath but are
 * only needed by other articles in the shared codebase.
 */
@SpringBootApplication(exclude = {
    OpenAiAutoConfiguration.class,
    ChromaVectorStoreAutoConfiguration.class
})
@PropertySource("classpath:application-anthropic.properties")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}