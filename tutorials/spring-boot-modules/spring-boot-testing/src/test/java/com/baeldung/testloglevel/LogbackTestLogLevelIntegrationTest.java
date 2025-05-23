package com.baeldung.testloglevel;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.OAuth2ResourceServerAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.system.OutputCaptureRule;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = TestLogLevelApplication.class)
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class, OAuth2ResourceServerAutoConfiguration.class })
@ActiveProfiles("logback-test")
public class LogbackTestLogLevelIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Rule
    public OutputCaptureRule outputCapture = new OutputCaptureRule();

    private String baseUrl = "/testLogLevel";

    @Test
    public void givenErrorRootLevelAndDebugLevelForOurPackage_whenCall_thenPrintDebugLogsForOurPackage() {
        ResponseEntity<String> response = restTemplate.getForEntity(baseUrl, String.class);

        assertThat(response.getStatusCode()
            .value()).isEqualTo(200);
        assertThatOutputContainsLogForOurPackage("DEBUG");
    }

    @Test
    public void givenErrorRootLevelAndDebugLevelForOurPackage_whenCall_thenNoDebugLogsForOtherPackages() {
        ResponseEntity<String> response = restTemplate.getForEntity(baseUrl, String.class);

        assertThat(response.getStatusCode()
            .value()).isEqualTo(200);
        assertThatOutputDoesntContainLogForOtherPackages("DEBUG");
    }

    @Test
    public void givenErrorRootLevelAndDebugLevelForOurPackage_whenCall_thenPrintErrorLogs() {
        ResponseEntity<String> response = restTemplate.getForEntity(baseUrl, String.class);

        assertThat(response.getStatusCode()
            .value()).isEqualTo(200);
        assertThatOutputContainsLogForOurPackage("ERROR");
        assertThatOutputContainsLogForOtherPackages("ERROR");
    }

    private void assertThatOutputContainsLogForOurPackage(String level) {
        assertThat(outputCapture.toString()).containsPattern("TestLogLevelController.*" + level + ".*");
    }

    private void assertThatOutputDoesntContainLogForOtherPackages(String level) {
        assertThat(outputCapture.toString()
            .replaceAll("(?m)^.*TestLogLevelController.*$", "")).doesNotContain(level);
    }

    private void assertThatOutputContainsLogForOtherPackages(String level) {
        assertThat(outputCapture.toString()
            .replaceAll("(?m)^.*TestLogLevelController.*$", "")).contains(level);
    }

}
