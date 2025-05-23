package com.baeldung.mockito.argumentcaptor;

public class Credentials {
    private final String name;
    private final String password;
    private final String key;

    public Credentials(String name, String password, String key) {
        this.name = name;
        this.password = password;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getKey() {
        return key;
    }
}
