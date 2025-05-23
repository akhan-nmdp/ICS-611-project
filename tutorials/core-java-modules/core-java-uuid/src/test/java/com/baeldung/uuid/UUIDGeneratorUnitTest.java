package com.baeldung.uuid;

import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UUIDGeneratorUnitTest {

    private static final String NAMESPACE_URL = "6ba7b811-9dad-11d1-80b4-00c04fd430c8";
    private static final String NAMESPACE_DNS = "6ba7b810-9dad-11d1-80b4-00c04fd430c8";

    @Test
    void shouldGenerateType1UUIDWithCorrectVersionAndVariant() {
        UUID uuid = UUIDGenerator.generateType1UUID();
        assertEquals(36, uuid.toString().length());
        assertEquals(1, uuid.version());
        assertEquals(2, uuid.variant());
    }

    @Test
    void shouldGenerateType1UUIDWithTheCurrentDate() {
        UUID uuid = UUIDGenerator.generateType1UUID();
        long time = uuid.timestamp();
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault());
        assertEquals(LocalDate.now(), dateTime.toLocalDate());
    }

    @Test
    void version_3_UUID_is_correctly_generated_for_domain_baeldung_com() {

        UUID uuid = UUIDGenerator.generateType3UUID(NAMESPACE_DNS, "baeldung.com");

        assertEquals("23785b78-0132-3ac6-aff6-cfd5be162139", uuid.toString());
        assertEquals(3, uuid.version());
        assertEquals(2, uuid.variant());
    }

    @Test
    void version_3_UUID_is_correctly_generated_for_domain_d() {

        UUID uuid = UUIDGenerator.generateType3UUID(NAMESPACE_DNS, "d");

        assertEquals("dbd41ecb-f466-33de-b309-1468addfc63b", uuid.toString());
        assertEquals(3, uuid.version());
        assertEquals(2, uuid.variant());
    }

    @Test
    public void version_4_UUID_is_generated_with_correct_length_version_and_variant() {

        UUID uuid = UUIDGenerator.generateType4UUID();

        assertEquals(36, uuid.toString().length());
        assertEquals(4, uuid.version());
        assertEquals(2, uuid.variant());
    }

    @Test
    public void version_5_UUID_is_correctly_generated_for_domain_baeldung_com() throws UnsupportedEncodingException {

        UUID uuid = UUIDGenerator.generateType5UUID(NAMESPACE_URL, "baeldung.com");

        assertEquals("aeff44a5-8a61-52b6-bcbe-c8e5bd7d0300", uuid.toString());
        assertEquals(5, uuid.version());
        assertEquals(2, uuid.variant());
    }

    @Test
    public void version_5_UUID_is_correctly_generated_for_domain_baeldung_name() {

        UUID uuid = UUIDGenerator.generateType5UUID("baeldung.com");

        assertEquals("efd5462b-b07a-52a3-94ea-bf575c0e0e75", uuid.toString());
        assertEquals(5, uuid.version());
        assertEquals(2, uuid.variant());
    }
}