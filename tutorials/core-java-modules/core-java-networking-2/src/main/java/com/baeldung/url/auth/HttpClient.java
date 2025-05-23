package com.baeldung.url.auth;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.binary.Base64;

public class HttpClient {

    private final String user;
    private final String password;

    public HttpClient(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public int sendRequestWithAuthHeader(String url) throws IOException {
        HttpURLConnection connection = null;
        try {
            connection = createConnection(url);
            connection.setRequestProperty("Authorization", createBasicAuthHeaderValue());
            return connection.getResponseCode();
        } catch (URISyntaxException e) {
            throw new IOException(e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public int sendRequestWithAuthenticator(String url) throws IOException, URISyntaxException {
        setAuthenticator();

        HttpURLConnection connection = null;
        try {
            connection = createConnection(url);
            return connection.getResponseCode();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private HttpURLConnection createConnection(String urlString) throws MalformedURLException, IOException, URISyntaxException {
        URL url = new URI(String.format(urlString)).toURL();
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        return connection;
    }

    private String createBasicAuthHeaderValue() {
        String auth = user + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.UTF_8));
        String authHeaderValue = "Basic " + new String(encodedAuth);
        return authHeaderValue;
    }

    private void setAuthenticator() {
        Authenticator.setDefault(new BasicAuthenticator());
    }

    private final class BasicAuthenticator extends Authenticator {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(user, password.toCharArray());
        }
    }
}
