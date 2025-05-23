package com.baeldung.maven.it;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class RestIT {
    @Test
    public void whenSendingGet_thenMessageIsReturned() throws Exception {
        String url = "http://localhost:8999";
        URLConnection connection = new URI(url).toURL().openConnection();
        try (InputStream response = connection.getInputStream();
             Scanner scanner = new Scanner(response)) {
            String responseBody = scanner.nextLine();
            assertEquals("Welcome to Baeldung!", responseBody);
        }
    }
}
