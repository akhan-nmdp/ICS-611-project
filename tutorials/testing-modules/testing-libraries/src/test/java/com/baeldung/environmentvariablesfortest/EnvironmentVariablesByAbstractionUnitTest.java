package com.baeldung.environmentvariablesfortest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class EnvironmentVariablesByAbstractionUnitTest {

    @FunctionalInterface
    interface GetEnv {
        String get(String name);
    }

    static class ReadsEnvironment {
        private GetEnv getEnv;

        public ReadsEnvironment(GetEnv getEnv) {
            this.getEnv = getEnv;
        }

        public String whatOs() {
            return getEnv.get("OS");
        }
    }

    @Test
    void givenFakeOs_thenCanReadIt() {
        Map<String, String> fakeEnv = new HashMap<>();
        fakeEnv.put("OS", "MacDowsNix");

        ReadsEnvironment reader = new ReadsEnvironment(fakeEnv::get);
        assertThat(reader.whatOs()).isEqualTo("MacDowsNix");
    }
}
