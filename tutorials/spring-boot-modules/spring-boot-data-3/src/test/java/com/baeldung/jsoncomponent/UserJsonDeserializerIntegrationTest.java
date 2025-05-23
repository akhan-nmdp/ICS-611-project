package com.baeldung.jsoncomponent;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.scene.paint.Color;

@JsonTest
@RunWith(SpringRunner.class)
public class UserJsonDeserializerIntegrationTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testDeserialize() throws IOException {
        User user = objectMapper.readValue("{\"favoriteColor\":\"#f0f8ff\"}", User.class);
        assertEquals(Color.ALICEBLUE, user.getFavoriteColor());
    }
}