package com.baeldung.client;

import java.io.IOException;

import com.baeldung.spring.ClientConfig;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.HttpResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ClientConfig.class}, loader = AnnotationConfigContextLoader.class)
public class RawClientLiveTest {

    // tests

    @Test
    public void whenSecuredRestApiIsConsumed_then200OK() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet getMethod = new HttpGet("http://localhost:8082/spring-security-rest-basic-auth/api/bars/1");
        HttpResponse response = httpClient.execute(getMethod);
        System.out.println("HTTP Status of response: " + response.getCode());
    }

}
