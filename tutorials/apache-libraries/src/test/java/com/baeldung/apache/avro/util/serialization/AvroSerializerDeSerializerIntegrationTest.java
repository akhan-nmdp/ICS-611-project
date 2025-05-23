package com.baeldung.apache.avro.util.serialization;

import com.baeldung.apache.avro.generated.Active;
import com.baeldung.apache.avro.generated.AvroHttpRequest;

import com.baeldung.apache.avro.generated.ClientIdentifier;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

public class AvroSerializerDeSerializerIntegrationTest {

    AvroSerializer serializer;
    AvroDeSerializer deserializer;
    AvroHttpRequest request;

    @Before
    public void setUp() {
        serializer = new AvroSerializer();
        deserializer = new AvroDeSerializer();

        ClientIdentifier clientIdentifier = ClientIdentifier.newBuilder()
            .setHostName("localhost")
            .setIpAddress("255.255.255.0")
            .build();

        List<String> employees = new ArrayList();
        employees.add("James");
        employees.add("Alice");
        employees.add("David");
        employees.add("Han");

        request = AvroHttpRequest.newBuilder()
            .setRequestTime(01l)
            .setActive(Active.YES)
            .setClientIdentifier(clientIdentifier)
            .setEmployeeNames(employees)
            .build();
    }

    @Test
    public void givenJSONEncoder_whenSerialized_thenObjectGetsSerialized() {
        byte[] data = serializer.serializeAvroHttpRequestJSON(request);
        assertTrue(Objects.nonNull(data));
        assertTrue(data.length > 0);
    }

    @Test
    public void givenBinaryEncoder_whenSerialized_thenObjectGetsSerialized() {
        byte[] data = serializer.serializeAvroHttpRequestBinary(request);
        assertTrue(Objects.nonNull(data));
        assertTrue(data.length > 0);
    }

    @Test
    public void givenJSONDecoder_whenDeserialize_thenActualAndExpectedObjectsAreEqual() {
        byte[] data = serializer.serializeAvroHttpRequestJSON(request);
        AvroHttpRequest actualRequest = deserializer.deSerializeAvroHttpRequestJSON(data);
        assertEquals(actualRequest, request);
        assertTrue(Long.valueOf(actualRequest.getRequestTime())
            .equals(request.getRequestTime()));
    }

    @Test
    public void givenBinaryEncoder_whenDeserialize_thenActualAndExpectedObjectsAreEqual() {
        byte[] data = serializer.serializeAvroHttpRequestBinary(request);
        AvroHttpRequest actualRequest = deserializer.deSerializeAvroHttpRequestBinary(data);
        assertEquals(actualRequest, request);
        assertTrue(Long.valueOf(actualRequest.getRequestTime())
            .equals(request.getRequestTime()));
    }

}

