package com.baeldung.nestedclass;

import org.junit.Test;

public class Enclosing {

    private static int x = 1;

    public static class StaticNested {

        private void run() {
            System.out.println("x = " + x);
        }
    }

    @Test
    public void test() {
        StaticNested nested = new StaticNested();
        nested.run();
    }
}
