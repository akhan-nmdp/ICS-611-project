package com.baeldung.resilience4j.eventendpoints;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.HttpStatus.*;

import com.baeldung.resilience4j.eventendpoints.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.IntStream;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

/**
 * This was failing as a unit test in integrated environment
 * probably due to parallel execution of tests.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ResilientAppControllerManualTest {

  private final Logger LOGGER = LoggerFactory.getLogger(getClass());

  @Autowired private TestRestTemplate restTemplate;

  @LocalServerPort private Integer port;

  private static final ObjectMapper objectMapper =
      new ObjectMapper().registerModule(new JavaTimeModule());

  @RegisterExtension
  static WireMockExtension EXTERNAL_SERVICE =
      WireMockExtension.newInstance()
          .options(WireMockConfiguration.wireMockConfig().port(9090))
          .build();

  @Test
  void testCircuitBreakerEvents() throws Exception {
    EXTERNAL_SERVICE.stubFor(WireMock.get("/api/external").willReturn(serverError()));

    IntStream.rangeClosed(1, 5)
        .forEach(
            i -> {
              ResponseEntity<String> response =
                  restTemplate.getForEntity("/api/circuit-breaker", String.class);
              assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
            });

    // Fetch the events generated by the above calls
    List<CircuitBreakerEvent> circuitBreakerEvents = getCircuitBreakerEvents();
    assertThat(circuitBreakerEvents.size()).isEqualTo(7);

    // The first 5 events are the error events corresponding to the above server error responses
    IntStream.rangeClosed(0, 4)
        .forEach(
            i -> {
              assertThat(circuitBreakerEvents.get(i).getCircuitBreakerName())
                  .isEqualTo("externalService");
              assertThat(circuitBreakerEvents.get(i).getType()).isEqualTo("ERROR");
              assertThat(circuitBreakerEvents.get(i).getCreationTime()).isNotNull();
              assertThat(circuitBreakerEvents.get(i).getErrorMessage()).isNotNull();
              assertThat(circuitBreakerEvents.get(i).getDurationInMs()).isNotNull();
              assertThat(circuitBreakerEvents.get(i).getStateTransition()).isNull();
            });

    // Following event signals the configured failure rate exceeded
    CircuitBreakerEvent failureRateExceededEvent = circuitBreakerEvents.get(5);
    assertThat(failureRateExceededEvent.getCircuitBreakerName()).isEqualTo("externalService");
    assertThat(failureRateExceededEvent.getType()).isEqualTo("FAILURE_RATE_EXCEEDED");
    assertThat(failureRateExceededEvent.getCreationTime()).isNotNull();
    assertThat(failureRateExceededEvent.getErrorMessage()).isNull();
    assertThat(failureRateExceededEvent.getDurationInMs()).isNull();
    assertThat(failureRateExceededEvent.getStateTransition()).isNull();

    // Following event signals the state transition from CLOSED TO OPEN
    CircuitBreakerEvent stateTransitionEvent = circuitBreakerEvents.get(6);
    assertThat(stateTransitionEvent.getCircuitBreakerName()).isEqualTo("externalService");
    assertThat(stateTransitionEvent.getType()).isEqualTo("STATE_TRANSITION");
    assertThat(stateTransitionEvent.getCreationTime()).isNotNull();
    assertThat(stateTransitionEvent.getErrorMessage()).isNull();
    assertThat(stateTransitionEvent.getDurationInMs()).isNull();
    assertThat(stateTransitionEvent.getStateTransition()).isEqualTo("CLOSED_TO_OPEN");

    IntStream.rangeClosed(1, 5)
        .forEach(
            i -> {
              ResponseEntity<String> response =
                  restTemplate.getForEntity("/api/circuit-breaker", String.class);
              assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SERVICE_UNAVAILABLE);
            });

    /// Fetch the events generated by the above calls
    List<CircuitBreakerEvent> updatedCircuitBreakerEvents = getCircuitBreakerEvents();
    assertThat(updatedCircuitBreakerEvents.size()).isEqualTo(12);

    // Newly added events will be of type NOT_PERMITTED since the Circuit Breaker is in OPEN state
    IntStream.rangeClosed(7, 11)
        .forEach(
            i -> {
              assertThat(updatedCircuitBreakerEvents.get(i).getCircuitBreakerName())
                  .isEqualTo("externalService");
              assertThat(updatedCircuitBreakerEvents.get(i).getType()).isEqualTo("NOT_PERMITTED");
              assertThat(updatedCircuitBreakerEvents.get(i).getCreationTime()).isNotNull();
              assertThat(updatedCircuitBreakerEvents.get(i).getErrorMessage()).isNull();
              assertThat(updatedCircuitBreakerEvents.get(i).getDurationInMs()).isNull();
              assertThat(updatedCircuitBreakerEvents.get(i).getStateTransition()).isNull();
            });

    EXTERNAL_SERVICE.verify(5, getRequestedFor(urlEqualTo("/api/external")));
  }

  private List<CircuitBreakerEvent> getCircuitBreakerEvents() throws Exception {
    String jsonEventsList =
        IOUtils.toString(
            new URI("http://localhost:" + port + "/actuator/circuitbreakerevents"), StandardCharsets.UTF_8);
    CircuitBreakerEvents circuitBreakerEvents =
        objectMapper.readValue(jsonEventsList, CircuitBreakerEvents.class);
    return circuitBreakerEvents.getCircuitBreakerEvents();
  }

  @Test
  void testRetryEvents() throws Exception {
    EXTERNAL_SERVICE.stubFor(WireMock.get("/api/external").willReturn(ok()));
    ResponseEntity<String> response1 = restTemplate.getForEntity("/api/retry", String.class);
    EXTERNAL_SERVICE.verify(1, getRequestedFor(urlEqualTo("/api/external")));

    EXTERNAL_SERVICE.resetRequests();

    EXTERNAL_SERVICE.stubFor(WireMock.get("/api/external").willReturn(serverError()));
    ResponseEntity<String> response2 = restTemplate.getForEntity("/api/retry", String.class);
    assertThat(response2.getBody()).isEqualTo("all retries have exhausted");
    EXTERNAL_SERVICE.verify(3, getRequestedFor(urlEqualTo("/api/external")));

    List<RetryEvent> retryEvents = getRetryEvents();
    assertThat(retryEvents.size()).isEqualTo(3);

    // First 2 events should be retry events
    IntStream.rangeClosed(0, 1)
        .forEach(
            i -> {
              assertThat(retryEvents.get(i).getRetryName()).isEqualTo("externalService");
              assertThat(retryEvents.get(i).getType()).isEqualTo("RETRY");
              assertThat(retryEvents.get(i).getCreationTime()).isNotNull();
              assertThat(retryEvents.get(i).getErrorMessage()).isNotNull();
              assertThat(retryEvents.get(i).getNumberOfAttempts()).isEqualTo(i + 1);
            });

    // Last event should be an error event because the configured num of retries is reached
    RetryEvent errorRetryEvent = retryEvents.get(2);
    assertThat(errorRetryEvent.getRetryName()).isEqualTo("externalService");
    assertThat(errorRetryEvent.getType()).isEqualTo("ERROR");
    assertThat(errorRetryEvent.getCreationTime()).isNotNull();
    assertThat(errorRetryEvent.getErrorMessage()).isNotNull();
    assertThat(errorRetryEvent.getNumberOfAttempts()).isEqualTo(3);
  }

  private List<RetryEvent> getRetryEvents() throws Exception {
    String jsonEventsList =
        IOUtils.toString(
            new URI("http://localhost:" + port + "/actuator/retryevents"), StandardCharsets.UTF_8);
    RetryEvents retryEvents = objectMapper.readValue(jsonEventsList, RetryEvents.class);
    return retryEvents.getRetryEvents();
  }

  @Test
  void testTimeLimiterEvents() throws Exception {
    EXTERNAL_SERVICE.stubFor(WireMock.get("/api/external").willReturn(ok()));
    ResponseEntity<String> response = restTemplate.getForEntity("/api/time-limiter", String.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.REQUEST_TIMEOUT);
    EXTERNAL_SERVICE.verify(1, getRequestedFor(urlEqualTo("/api/external")));

    List<TimeLimiterEvent> timeLimiterEvents = getTimeLimiterEvents();
    assertThat(timeLimiterEvents.size()).isEqualTo(1);
    TimeLimiterEvent timeoutEvent = timeLimiterEvents.get(0);
    assertThat(timeoutEvent.getTimeLimiterName()).isEqualTo("externalService");
    assertThat(timeoutEvent.getType()).isEqualTo("TIMEOUT");
    assertThat(timeoutEvent.getCreationTime()).isNotNull();
  }

  private List<TimeLimiterEvent> getTimeLimiterEvents() throws Exception {
    String jsonEventsList =
        IOUtils.toString(
            new URI("http://localhost:" + port + "/actuator/timelimiterevents"), StandardCharsets.UTF_8);
    TimeLimiterEvents timeLimiterEvents =
        objectMapper.readValue(jsonEventsList, TimeLimiterEvents.class);
    return timeLimiterEvents.getTimeLimiterEvents();
  }

  @Test
  void testBulkheadEvents() throws Exception {
    EXTERNAL_SERVICE.stubFor(WireMock.get("/api/external").willReturn(ok()));
    Map<Integer, Integer> responseStatusCount = new ConcurrentHashMap<>();
    ExecutorService executorService = Executors.newFixedThreadPool(5);
    CountDownLatch latch = new CountDownLatch(5);

    IntStream.rangeClosed(1, 5)
        .forEach(
            i ->
                executorService.execute(
                    () -> {
                      ResponseEntity<String> response =
                          restTemplate.getForEntity("/api/bulkhead", String.class);
                      int statusCode = response.getStatusCodeValue();
                      responseStatusCount.merge(statusCode, 1, Integer::sum);
                      latch.countDown();
                    }));
    latch.await();
    executorService.shutdown();

    assertEquals(2, responseStatusCount.keySet().size());
    LOGGER.info("Response statuses: " + responseStatusCount.keySet());
    assertTrue(responseStatusCount.containsKey(BANDWIDTH_LIMIT_EXCEEDED.value()));
    assertTrue(responseStatusCount.containsKey(OK.value()));
    EXTERNAL_SERVICE.verify(3, getRequestedFor(urlEqualTo("/api/external")));

    List<BulkheadEvent> bulkheadEvents = getBulkheadEvents();

    // Based on the configuration, the first 3 calls should be permitted, so we should see the
    // CALL_PERMITTED events
    IntStream.rangeClosed(0, 2)
        .forEach(
            i -> {
              assertThat(bulkheadEvents.get(i).getBulkheadName()).isEqualTo("externalService");
              assertThat(bulkheadEvents.get(i).getType()).isEqualTo("CALL_PERMITTED");
              assertThat(bulkheadEvents.get(i).getCreationTime()).isNotNull();
            });

    // For the other 2 calls made we should see the CALL_REJECTED events
    IntStream.rangeClosed(3, 4)
        .forEach(
            i -> {
              assertThat(bulkheadEvents.get(i).getBulkheadName()).isEqualTo("externalService");
              assertThat(bulkheadEvents.get(i).getType()).isEqualTo("CALL_REJECTED");
              assertThat(bulkheadEvents.get(i).getCreationTime()).isNotNull();
            });
  }

  private List<BulkheadEvent> getBulkheadEvents() throws Exception {
    String jsonEventsList =
        IOUtils.toString(
            new URI("http://localhost:" + port + "/actuator/bulkheadevents"), StandardCharsets.UTF_8);
    BulkheadEvents bulkheadEvents = objectMapper.readValue(jsonEventsList, BulkheadEvents.class);
    return bulkheadEvents.getBulkheadEvents();
  }

  @Test
  void testRateLimiterEvents() throws Exception {
    EXTERNAL_SERVICE.stubFor(WireMock.get("/api/external").willReturn(ok()));
    Map<Integer, Integer> responseStatusCount = new ConcurrentHashMap<>();

    IntStream.rangeClosed(1, 50)
        .forEach(
            i -> {
              ResponseEntity<String> response =
                  restTemplate.getForEntity("/api/rate-limiter", String.class);
              int statusCode = response.getStatusCodeValue();
              responseStatusCount.put(
                  statusCode, responseStatusCount.getOrDefault(statusCode, 0) + 1);
            });

    assertEquals(2, responseStatusCount.keySet().size());
    assertTrue(responseStatusCount.containsKey(TOO_MANY_REQUESTS.value()));
    assertTrue(responseStatusCount.containsKey(OK.value()));
    EXTERNAL_SERVICE.verify(5, getRequestedFor(urlEqualTo("/api/external")));

    List<RateLimiterEvent> rateLimiterEvents = getRateLimiterEvents();
    assertThat(rateLimiterEvents.size()).isEqualTo(50);

    // First allowed calls in the rate limit is 5, so we should see for those SUCCESSFUL_ACQUIRE
    // events
    IntStream.rangeClosed(0, 4)
        .forEach(
            i -> {
              assertThat(rateLimiterEvents.get(i).getRateLimiterName())
                  .isEqualTo("externalService");
              assertThat(rateLimiterEvents.get(i).getType()).isEqualTo("SUCCESSFUL_ACQUIRE");
              assertThat(rateLimiterEvents.get(i).getCreationTime()).isNotNull();
            });

    // the rest should be FAILED_ACQUIRE events since the rate limiter kicks in
    IntStream.rangeClosed(5, rateLimiterEvents.size() - 1)
        .forEach(
            i -> {
              assertThat(rateLimiterEvents.get(i).getRateLimiterName())
                  .isEqualTo("externalService");
              assertThat(rateLimiterEvents.get(i).getType()).isEqualTo("FAILED_ACQUIRE");
              assertThat(rateLimiterEvents.get(i).getCreationTime()).isNotNull();
            });
  }

  private List<RateLimiterEvent> getRateLimiterEvents() throws Exception {
    String jsonEventsList =
        IOUtils.toString(
            new URI("http://localhost:" + port + "/actuator/ratelimiterevents"), StandardCharsets.UTF_8);
    RateLimiterEvents rateLimiterEvents =
        objectMapper.readValue(jsonEventsList, RateLimiterEvents.class);
    return rateLimiterEvents.getRateLimiterEvents();
  }
}
