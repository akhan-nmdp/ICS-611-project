package com.baeldung.boot.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class SkillTag {
    private String name;
    private int value;

    public SkillTag() {
    }

    public SkillTag(String name, int value) {
        super();
        this.name = name;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }
}
