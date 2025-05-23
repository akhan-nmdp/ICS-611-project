package com.baeldung.exceptions.sneakythrows;

import static com.baeldung.exceptions.sneakythrows.SneakyThrowsExamples.throwSneakyIOException;
import static com.baeldung.exceptions.sneakythrows.SneakyThrowsExamples.throwSneakyIOExceptionUsingLombok;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.IOException;

import org.junit.Test;

public class SneakyThrowsExamplesUnitTest {

    @Test
    public void throwSneakyIOException_IOExceptionShouldBeThrown() {
        assertThatThrownBy(() -> throwSneakyIOException())
            .isInstanceOf(IOException.class)
            .hasMessage("sneaky")
            .hasStackTraceContaining("SneakyThrowsExamples.throwSneakyIOException");
    }

    @Test
    public void throwSneakyIOExceptionUsingLombok_IOExceptionShouldBeThrown() {
        assertThatThrownBy(() -> throwSneakyIOExceptionUsingLombok())
            .isInstanceOf(IOException.class)
            .hasMessage("lombok sneaky")
            .hasStackTraceContaining("SneakyThrowsExamples.throwSneakyIOExceptionUsingLombok");
    }
}
