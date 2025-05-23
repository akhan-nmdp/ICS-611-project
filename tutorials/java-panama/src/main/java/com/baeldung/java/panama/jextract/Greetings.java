package com.baeldung.java.panama.jextract;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.Arena;
// Generate JExtract bindings before uncommenting
// import static foreign.c.stdio_h.printf;

public class Greetings {

    public static void main(String[] args) {
        String greeting = "Hello World from Project Panama Baeldung Article, using JExtract!";

        try (Arena memorySession = Arena.ofConfined()) {
            MemorySegment greetingSegment = memorySession.allocateUtf8String(greeting);
            // Generate JExtract bingings before uncommenting
            //  printf(greetingSegment);
        }
    }
}
