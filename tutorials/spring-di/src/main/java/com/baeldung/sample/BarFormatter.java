package com.baeldung.sample;

import org.springframework.stereotype.Component;

@FormatterType("Bar")
@Component
public class BarFormatter implements Formatter {

    public String format() {
        return "bar";
    }

}
