package com.baeldung.readertox;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.CharSequenceReader;
import org.apache.commons.io.input.ReaderInputStream;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Charsets;
import com.google.common.io.CharSink;
import com.google.common.io.CharSource;
import com.google.common.io.CharStreams;
import com.google.common.io.FileWriteMode;

@SuppressWarnings("unused")
public class JavaReaderToXUnitTest {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    private static final int DEFAULT_SIZE = 1500000;

    // tests - sandbox

    // tests - Reader WRITE TO File

    @Test
    public void givenUsingPlainJava_whenWritingReaderContentsToFile_thenCorrect() throws IOException {
        final Reader initialReader = new StringReader("Some text");

        int intValueOfChar;
        final StringBuilder buffer = new StringBuilder();
        while ((intValueOfChar = initialReader.read()) != -1) {
            buffer.append((char) intValueOfChar);
        }
        initialReader.close();

        final File targetFile = new File("src/test/resources/targetFile.txt");
        targetFile.createNewFile();

        final Writer targetFileWriter = new FileWriter(targetFile);
        targetFileWriter.write(buffer.toString());
        targetFileWriter.close();
    }

    @Test
    public void givenUsingGuava_whenWritingReaderContentsToFile_thenCorrect() throws IOException {
        final Reader initialReader = new StringReader("Some text");

        final File targetFile = new File("src/test/resources/targetFile.txt");
        com.google.common.io.Files.touch(targetFile);
        final CharSink charSink = com.google.common.io.Files.asCharSink(targetFile, Charset.defaultCharset(), FileWriteMode.APPEND);
        charSink.writeFrom(initialReader);
        initialReader.close();
    }

    @Test
    public void givenUsingCommonsIO_whenWritingReaderContentsToFile_thenCorrect() throws IOException {
        final Reader initialReader = new CharSequenceReader("CharSequenceReader extends Reader");

        final File targetFile = new File("src/test/resources/targetFile.txt");
        FileUtils.touch(targetFile);
        final byte[] buffer = IOUtils.toByteArray(initialReader);
        FileUtils.writeByteArrayToFile(targetFile, buffer);
        initialReader.close();
    }

    // tests - Reader to byte[]

    @Test
    public void givenUsingPlainJava_whenConvertingReaderIntoByteArray_thenCorrect() throws IOException {
        final Reader initialReader = new StringReader("With Java");

        final char[] charArray = new char[8 * 1024];
        final StringBuilder builder = new StringBuilder();
        int numCharsRead;
        while ((numCharsRead = initialReader.read(charArray, 0, charArray.length)) != -1) {
            builder.append(charArray, 0, numCharsRead);
        }
        final byte[] targetArray = builder.toString().getBytes();

        initialReader.close();
    }

    @Test
    public void givenUsingGuava_whenConvertingReaderIntoByteArray_thenCorrect() throws IOException {
        final Reader initialReader = CharSource.wrap("With Google Guava").openStream();

        final byte[] targetArray = CharStreams.toString(initialReader).getBytes();

        initialReader.close();
    }

    @Test
    public void givenUsingCommonsIO_whenConvertingReaderIntoByteArray_thenCorrect() throws IOException {
        final StringReader initialReader = new StringReader("With Commons IO");

        final byte[] targetArray = IOUtils.toByteArray(initialReader);

        initialReader.close();
    }

    // tests - Reader to InputStream

    @Test
    public void givenUsingPlainJava_whenConvertingReaderIntoInputStream_thenCorrect() throws IOException {
        final Reader initialReader = new StringReader("With Java");

        final char[] charBuffer = new char[8 * 1024];
        final StringBuilder builder = new StringBuilder();
        int numCharsRead;
        while ((numCharsRead = initialReader.read(charBuffer, 0, charBuffer.length)) != -1) {
            builder.append(charBuffer, 0, numCharsRead);
        }
        final InputStream targetStream = new ByteArrayInputStream(builder.toString().getBytes());

        initialReader.close();
        targetStream.close();
    }

    @Test
    public void givenUsingGuava_whenConvertingReaderIntoInputStream_thenCorrect() throws IOException {
        final Reader initialReader = new StringReader("With Guava");

        final InputStream targetStream = new ByteArrayInputStream(CharStreams.toString(initialReader).getBytes());

        initialReader.close();
        targetStream.close();
    }

    @Test
    public void givenUsingCommonsIOUtils_whenConvertingReaderIntoInputStream() throws IOException {
        final Reader initialReader = new StringReader("With Commons IO");

        final InputStream targetStream = IOUtils.toInputStream(IOUtils.toString(initialReader));

        initialReader.close();
        targetStream.close();
    }

    @Test
    public void givenUsingCommonsIOUtils_whenConvertingReaderIntoInputStream_thenCorrect() throws IOException {
        String initialString = "With Commons IO";
        final Reader initialReader = new StringReader(initialString);

        final InputStream targetStream = IOUtils.toInputStream(IOUtils.toString(initialReader));

        final String finalString = IOUtils.toString(targetStream);
        assertThat(finalString, equalTo(initialString));

        initialReader.close();
        targetStream.close();
    }

    @Test
    public void givenUsingCommonsIOReaderInputStream_whenConvertingReaderIntoInputStream() throws IOException {
        final Reader initialReader = new StringReader("With Commons IO");

        final InputStream targetStream = new ReaderInputStream(initialReader);

        initialReader.close();
        targetStream.close();
    }

    @Test
    public void givenUsingCommonsIOReaderInputStream_whenConvertingReaderIntoInputStream_thenCorrect() throws IOException {
        String initialString = "With Commons IO";
        final Reader initialReader = new StringReader(initialString);

        final InputStream targetStream = new ReaderInputStream(initialReader);

        final String finalString = IOUtils.toString(targetStream);
        assertThat(finalString, equalTo(initialString));

        initialReader.close();
        targetStream.close();
    }

    // tests - Reader to InputStream with encoding

    @Test
    public void givenUsingPlainJava_whenConvertingReaderIntoInputStreamWithCharset() throws IOException {
        final Reader initialReader = new StringReader("With Java");

        final char[] charBuffer = new char[8 * 1024];
        final StringBuilder builder = new StringBuilder();
        int numCharsRead;
        while ((numCharsRead = initialReader.read(charBuffer, 0, charBuffer.length)) != -1) {
            builder.append(charBuffer, 0, numCharsRead);
        }
        final InputStream targetStream = new ByteArrayInputStream(builder.toString().getBytes(StandardCharsets.UTF_8));

        initialReader.close();
        targetStream.close();
    }

    @Test
    public void givenUsingGuava_whenConvertingReaderIntoInputStreamWithCharset_thenCorrect() throws IOException {
        final Reader initialReader = new StringReader("With Guava");

        final InputStream targetStream = new ByteArrayInputStream(CharStreams.toString(initialReader).getBytes(Charsets.UTF_8));

        initialReader.close();
        targetStream.close();
    }

    @Test
    public void givenUsingCommonsIOUtils_whenConvertingReaderIntoInputStreamWithEncoding() throws IOException {
        final Reader initialReader = new StringReader("With Commons IO");

        final InputStream targetStream = IOUtils.toInputStream(IOUtils.toString(initialReader), Charsets.UTF_8);

        initialReader.close();
        targetStream.close();
    }

    @Test
    public void givenUsingCommonsIOUtils_whenConvertingReaderIntoInputStreamWithEncoding_thenCorrect() throws IOException {
        String initialString = "With Commons IO";
        final Reader initialReader = new StringReader(initialString);
        final InputStream targetStream = IOUtils.toInputStream(IOUtils.toString(initialReader), Charsets.UTF_8);

        String finalString = IOUtils.toString(targetStream, Charsets.UTF_8);
        assertThat(finalString, equalTo(initialString));

        initialReader.close();
        targetStream.close();
    }

    @Test
    public void givenUsingCommonsIOReaderInputStream_whenConvertingReaderIntoInputStreamWithEncoding() throws IOException {
        final Reader initialReader = new StringReader("With Commons IO");

        final InputStream targetStream = new ReaderInputStream(initialReader, Charsets.UTF_8);

        initialReader.close();
        targetStream.close();
    }

    @Test
    public void givenUsingCommonsIOReaderInputStream_whenConvertingReaderIntoInputStreamWithEncoding_thenCorrect() throws IOException {
        String initialString = "With Commons IO";
        final Reader initialReader = new StringReader(initialString);

        final InputStream targetStream = new ReaderInputStream(initialReader, Charsets.UTF_8);

        String finalString = IOUtils.toString(targetStream, Charsets.UTF_8);
        assertThat(finalString, equalTo(initialString));

        initialReader.close();
        targetStream.close();
    }
}
