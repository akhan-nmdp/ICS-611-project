package com.baeldung.jsoncomponent;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.scene.paint.Color;

@JsonTest
@RunWith(SpringRunner.class)
public class UserJsonSerializerIntegrationTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testSerialization() throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(new User(Color.ALICEBLUE));
        assertEquals("{\"favoriteColor\":\"#f0f8ff\"}", json);
    }
}