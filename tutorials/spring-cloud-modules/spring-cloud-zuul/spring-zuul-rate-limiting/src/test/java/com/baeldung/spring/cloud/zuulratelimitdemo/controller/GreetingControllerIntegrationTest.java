package com.baeldung.spring.cloud.zuulratelimitdemo.controller;

import static com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.support.RateLimitConstants.HEADER_LIMIT;
import static com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.support.RateLimitConstants.HEADER_QUOTA;
import static com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.support.RateLimitConstants.HEADER_REMAINING;
import static com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.support.RateLimitConstants.HEADER_REMAINING_QUOTA;
import static com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.support.RateLimitConstants.HEADER_RESET;
import static java.lang.Integer.parseInt;
import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.TOO_MANY_REQUESTS;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@AutoConfigureTestDatabase
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GreetingControllerIntegrationTest {

    private static final String SIMPLE_GREETING = "/greeting/simple";
    private static final String ADVANCED_GREETING = "/greeting/advanced";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void whenRequestNotExceedingCapacity_thenReturnOkResponse() {
        ResponseEntity<String> response = this.restTemplate.getForEntity(SIMPLE_GREETING, String.class);
        HttpHeaders headers = response.getHeaders();
        String key = "rate-limit-application_serviceSimple_127.0.0.1";

        String limit = headers.getFirst(HEADER_LIMIT + key);
        String remaining = headers.getFirst(HEADER_REMAINING + key);
        String reset = headers.getFirst(HEADER_RESET + key);

        assertEquals("5", limit);
        assertEquals(remaining, "4", remaining);
        assertNotNull(reset);
        assertThat(
                parseInt(reset),
                is(both(greaterThanOrEqualTo(0)).and(lessThanOrEqualTo(60000)))
        );

        assertEquals(OK, response.getStatusCode());
    }

    @Test
    public void whenRequestExceedingCapacity_thenReturnTooManyRequestsResponse() throws InterruptedException {
        ResponseEntity<String> response = this.restTemplate.getForEntity(ADVANCED_GREETING, String.class);
        HttpHeaders headers = response.getHeaders();
        String key = "rate-limit-application_serviceAdvanced_127.0.0.1";
        assertHeaders(headers, key, false, false);
        assertEquals(OK, response.getStatusCode());

        for (int i = 0; i < 2; i++) {
            response = this.restTemplate.getForEntity(ADVANCED_GREETING, String.class);
        }

        headers = response.getHeaders();
        String limit = headers.getFirst(HEADER_LIMIT + key);
        String remaining = headers.getFirst(HEADER_REMAINING + key);
        String reset = headers.getFirst(HEADER_RESET + key);

        assertEquals(limit, "1");
        assertEquals(remaining, "0");
        assertNotEquals(reset, "2000");

        assertEquals(TOO_MANY_REQUESTS, response.getStatusCode());

        TimeUnit.SECONDS.sleep(2);

        response = this.restTemplate.getForEntity(ADVANCED_GREETING, String.class);
        headers = response.getHeaders();
        assertHeaders(headers, key, false, false);
        assertEquals(OK, response.getStatusCode());
    }

    private void assertHeaders(HttpHeaders headers, String key, boolean nullable, boolean quotaHeaders) {
        String quota = headers.getFirst(HEADER_QUOTA + key);
        String remainingQuota = headers.getFirst(HEADER_REMAINING_QUOTA + key);
        String limit = headers.getFirst(HEADER_LIMIT + key);
        String remaining = headers.getFirst(HEADER_REMAINING + key);
        String reset = headers.getFirst(HEADER_RESET + key);

        if (nullable) {
            if (quotaHeaders) {
                assertNull(quota);
                assertNull(remainingQuota);
            } else {
                assertNull(limit);
                assertNull(remaining);
            }
            assertNull(reset);
        } else {
            if (quotaHeaders) {
                assertNotNull(quota);
                assertNotNull(remainingQuota);
            } else {
                assertNotNull(limit);
                assertNotNull(remaining);
            }
            assertNotNull(reset);
        }
    }
}
