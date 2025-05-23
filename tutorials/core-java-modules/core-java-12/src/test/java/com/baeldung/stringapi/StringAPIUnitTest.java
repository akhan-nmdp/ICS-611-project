package com.baeldung.stringapi;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StringAPIUnitTest {

    @Test
    public void whenPositiveArgument_thenReturnIndentedString() {
        String multilineStr = "This is\na multiline\nstring.";
        String outputStr = "   This is\n   a multiline\n   string.\n";

        String postIndent = multilineStr.indent(3);

        assertThat(postIndent, equalTo(outputStr));
    }

    @Test
    public void whenNegativeArgument_thenReturnReducedIndentedString() {
        String multilineStr = "   This is\n   a multiline\n   string.";
        String outputStr = " This is\n a multiline\n string.\n";

        String postIndent = multilineStr.indent(-2);

        assertThat(postIndent, equalTo(outputStr));
    }

    @Test
    public void whenTransformUsingLamda_thenReturnTransformedString() {
        String result = "hello".transform(input -> input + " world!");

        assertThat(result, equalTo("hello world!"));
    }

    @Test
    public void whenTransformUsingParseInt_thenReturnInt() {
        int result = "42".transform(Integer::parseInt);

        assertThat(result, equalTo(42));
    }

    @Test
    public void whenRepeatStringTwice_thenGetStringTwice() {
        String output = "La ".repeat(2) + "Land";

        is(output).equals("La La Land");
    }

    @Test
    public void whenStripString_thenReturnStringWithoutWhitespaces() {
        is("\n\t  hello   \u2005".strip()).equals("hello");
    }

    @Test
    public void whenTrimAdvanceString_thenReturnStringWithWhitespaces() {
        is("\n\t  hello   \u2005".trim()).equals("hello   \u2005");
    }

    @Test
    public void whenBlankString_thenReturnTrue() {
        assertTrue("\n\t\u2005  ".isBlank());
    }

    @Test
    public void whenMultilineString_thenReturnNonEmptyLineCount() {
        String multilineStr = "This is\n \n a multiline\n string.";

        long lineCount = multilineStr.lines()
            .filter(String::isBlank)
            .count();

        is(lineCount).equals(3L);
    }
}
