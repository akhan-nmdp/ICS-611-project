package com.baeldung.exceptions;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;

public class JacksonExceptionsUnitTest {

    // JsonMappingException: Can not construct instance of
    @Test(expected = JsonMappingException.class)
    public void givenAbstractClass_whenDeserializing_thenException() throws IOException {
        final String json = "{\"animal\":{\"name\":\"lacy\"}}";
        final ObjectMapper mapper = new ObjectMapper();

        mapper.reader()
            .forType(Zoo.class)
            .readValue(json);
    }

    @Test
    public void givenAbstractClassConfigured_whenDeserializing_thenCorrect() throws IOException {
        final String json = "{\"animal\":{\"name\":\"lacy\"}}";
        final ObjectMapper mapper = new ObjectMapper();

        mapper.reader()
            .forType(ZooConfigured.class)
            .readValue(json);
    }

    // JsonMappingException: No serializer found for class
    @Test(expected = JsonMappingException.class)
    public void givenClassWithPrivateFields_whenSerializing_thenException() throws IOException {
        final UserWithPrivateFields user = new UserWithPrivateFields(1, "John");

        final ObjectMapper mapper = new ObjectMapper();
        mapper.writer()
            .writeValueAsString(user);
    }

    @Test
    public void givenClassWithPrivateFields_whenConfigureSerializing_thenCorrect() throws IOException {
        final UserWithPrivateFields user = new UserWithPrivateFields(1, "John");

        final ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);

        final String result = mapper.writer()
            .writeValueAsString(user);
        assertThat(result, containsString("John"));
    }

    // JsonMappingException: No suitable constructor found
    @Test(expected = JsonMappingException.class)
    public void givenNoDefaultConstructor_whenDeserializing_thenException() throws IOException {
        final String json = "{\"id\":1,\"name\":\"John\"}";
        final ObjectMapper mapper = new ObjectMapper();

        mapper.reader()
            .forType(UserWithNoDefaultConstructor.class)
            .readValue(json);
    }

    @Test
    public void givenJsonObject_whenDeserializingIntoString_thenException() throws IOException {
        final String json = "{\"firstName\":\"Azhrioun\",\"lastName\":\"Abderrahim\",\"contact\":{\"email\":\"azh@email.com\"}}";
        final ObjectMapper mapper = new ObjectMapper();

        Exception exception = assertThrows(JsonMappingException.class, () -> mapper.reader()
            .forType(Person.class)
            .readValue(json));

        assertTrue(exception.getMessage()
            .contains("Cannot deserialize value of type `java.lang.String` from Object value (token `JsonToken.START_OBJECT`)"));
    }

    @Test
    public void givenJsonObject_whenDeserializingIntoObject_thenDeserialize() throws IOException {
        final String json = "{\"firstName\":\"Azhrioun\",\"lastName\":\"Abderrahim\",\"contact\":{\"email\":\"azh@email.com\"}}";
        final ObjectMapper mapper = new ObjectMapper();

        PersonContact person = mapper.reader()
            .forType(PersonContact.class)
            .readValue(json);

        assertEquals("azh@email.com", person.getContact()
            .getEmail());
    }

    @Test
    public void givenDefaultConstructor_whenDeserializing_thenCorrect() throws IOException {
        final String json = "{\"id\":1,\"name\":\"John\"}";
        final ObjectMapper mapper = new ObjectMapper();

        final User user = mapper.reader()
            .forType(User.class)
            .readValue(json);
        assertEquals("John", user.name);
    }

    // JsonMappingException: Root name does not match expected
    @Test(expected = JsonMappingException.class)
    public void givenWrappedJsonString_whenDeserializing_thenException() throws IOException {
        final String json = "{\"user\":{\"id\":1,\"name\":\"John\"}}";

        final ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);

        mapper.reader()
            .forType(User.class)
            .readValue(json);
    }

    @Test
    public void givenWrappedJsonStringAndConfigureClass_whenDeserializing_thenCorrect() throws IOException {
        final String json = "{\"user\":{\"id\":1,\"name\":\"John\"}}";

        final ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);

        final UserWithRoot user = mapper.reader()
            .forType(UserWithRoot.class)
            .readValue(json);
        assertEquals("John", user.name);
    }

    // JsonMappingException: Can not deserialize instance of
    @Test(expected = JsonMappingException.class)
    public void givenJsonOfArray_whenDeserializing_thenException() throws JsonProcessingException, IOException {
        final String json = "[{\"id\":1,\"name\":\"John\"},{\"id\":2,\"name\":\"Adam\"}]";
        final ObjectMapper mapper = new ObjectMapper();

        mapper.reader()
            .forType(User.class)
            .readValue(json);
    }

    @Test
    public void givenJsonOfArray_whenDeserializing_thenCorrect() throws JsonProcessingException, IOException {
        final String json = "[{\"id\":1,\"name\":\"John\"},{\"id\":2,\"name\":\"Adam\"}]";
        final ObjectMapper mapper = new ObjectMapper();

        final List<User> users = mapper.reader()
            .forType(new TypeReference<List<User>>() {
            })
            .readValue(json);

        assertEquals(2, users.size());
    }

    // UnrecognizedPropertyException
    @Test(expected = UnrecognizedPropertyException.class)
    public void givenJsonStringWithExtra_whenDeserializing_thenException() throws IOException {
        final String json = "{\"id\":1,\"name\":\"John\", \"checked\":true}";

        final ObjectMapper mapper = new ObjectMapper();
        mapper.reader()
            .forType(User.class)
            .readValue(json);
    }

    @Test
    public void givenJsonStringWithExtra_whenConfigureDeserializing_thenCorrect() throws IOException {
        final String json = "{\"id\":1,\"name\":\"John\", \"checked\":true}";

        final ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        final User user = mapper.reader()
            .forType(User.class)
            .readValue(json);
        assertEquals("John", user.name);
    }

    // JsonParseException: Unexpected character (''' (code 39))
    @Test(expected = JsonParseException.class)
    public void givenStringWithSingleQuotes_whenDeserializing_thenException() throws JsonProcessingException, IOException {
        final String json = "{'id':1,'name':'John'}";
        final ObjectMapper mapper = new ObjectMapper();

        mapper.reader()
            .forType(User.class)
            .readValue(json);
    }

    // JsonParseException: Unexpected character ('n' (code 110))
    @Test(expected = JsonParseException.class)
    public void givenInvalidJsonString_whenDeserializing_thenException() throws JsonProcessingException, IOException {
        final String json = "{\"id\":1, name:\"John\"}"; // Missing double quotes around 'name'
        final ObjectMapper mapper = new ObjectMapper();
        mapper.reader()
            .forType(User.class)
            .readValue(json);
    }

    @Test
    public void givenStringWithSingleQuotes_whenConfigureDeserializing_thenCorrect() throws JsonProcessingException, IOException {
        final String json = "{'id':1,'name':'John'}";

        final JsonFactory factory = new JsonFactory();
        factory.enable(JsonParser.Feature.ALLOW_SINGLE_QUOTES);
        final ObjectMapper mapper = new ObjectMapper(factory);

        final User user = mapper.reader()
            .forType(User.class)
            .readValue(json);
        assertEquals("John", user.name);
    }

}
