package com.baeldung.resttemplate.proxy;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.net.Proxy;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.routing.DefaultProxyRoutePlanner;
import org.apache.hc.core5.http.HttpHost;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * This class is used to test a request using {@link RestTemplate} with {@link Proxy}
 * using a {@link RestTemplateCustomizer} as configuration. 
 * <br />
 * <br />
 *
 * Before running the test we should change the <code>PROXY_SERVER_HOST</code> 
 * and <code>PROXY_SERVER_PORT</code> constants in our class to match our preferred proxy configuration.
 */
public class RestTemplateCustomizerLiveTest {

    private static final String PROXY_SERVER_HOST = "127.0.0.1";
    private static final int PROXY_SERVER_PORT = 8080;

    RestTemplate restTemplate;

    @Before
    public void setUp() {
        restTemplate = new RestTemplateBuilder(new ProxyCustomizer()).build();
    }

    @Test
    public void givenRestTemplate_whenRequestedWithProxy_thenResponseBodyIsOk() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://httpbin.org/get", String.class);

        assertThat(responseEntity.getStatusCode(), is(equalTo(HttpStatus.OK)));
    }

    private static class ProxyCustomizer implements RestTemplateCustomizer {

        @Override
        public void customize(RestTemplate restTemplate) {
            HttpHost proxy = new HttpHost(PROXY_SERVER_HOST, PROXY_SERVER_PORT);
            HttpClient httpClient = HttpClientBuilder.create()
                .setRoutePlanner(new DefaultProxyRoutePlanner(proxy))
                .build();
            restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(httpClient));
        }
    }
}
