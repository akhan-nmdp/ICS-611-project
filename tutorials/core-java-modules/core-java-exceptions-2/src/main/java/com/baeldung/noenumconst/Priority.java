package com.baeldung.noenumconst;

public enum Priority {

    HIGH("High"), MEDIUM("Medium"), LOW("Low");

    private String name;

    Priority(String name) {
        this.name = name;
    }

}
