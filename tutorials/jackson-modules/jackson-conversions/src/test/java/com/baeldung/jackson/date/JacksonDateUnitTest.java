package com.baeldung.jackson.date;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JacksonDateUnitTest {

    @Test
    public void whenSerializingDateWithJackson_thenSerializedToTimestamp() throws JsonProcessingException, ParseException {
        final SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));

        final String toParse = "01-01-1970 01:00";
        final Date date = df.parse(toParse);
        final Event event = new Event("party", date);

        final ObjectMapper mapper = new ObjectMapper();
        final String result = mapper.writeValueAsString(event);
        assertThat(result, containsString("3600000"));
    }

    @Test
    public void whenSerializingDateToISO8601_thenSerializedToText() throws JsonProcessingException, ParseException {
        final SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));

        final String toParse = "01-01-1970 02:30";
        final Date date = df.parse(toParse);
        final Event event = new Event("party", date);

        final ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // StdDateFormat is ISO8601 since jackson 2.9
        mapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));

        final String result = mapper.writeValueAsString(event);
        assertThat(result, containsString("1970-01-01T02:30:00.000+00:00"));
    }
    
    @Test
    public void whenDeserialisingZonedDateTimeWithDefaults_thenNotCorrect()
      throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.enable(SerializationFeature.WRITE_DATES_WITH_ZONE_ID);
        objectMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("UTC"));
        String converted = objectMapper.writeValueAsString(now);
     
        ZonedDateTime restored = objectMapper.readValue(converted, ZonedDateTime.class);
        System.out.println("serialized: " + now);
        System.out.println("restored: " + restored);
        assertThat(now, is(restored));
    }

    @Test
    public void whenSettingObjectMapperDateFormat_thenCorrect() throws JsonProcessingException, ParseException {
        final SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm");

        final String toParse = "20-12-2014 02:30";
        final Date date = df.parse(toParse);
        final Event event = new Event("party", date);

        final ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(df);

        final String result = mapper.writeValueAsString(event);
        assertThat(result, containsString(toParse));
    }

    @Test
    public void whenUsingJsonFormatAnnotationToFormatDate_thenCorrect() throws JsonProcessingException, ParseException {
        final SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));

        final String toParse = "20-12-2014 02:30:00";
        final Date date = df.parse(toParse);
        final EventWithFormat event = new EventWithFormat("party", date);

        final ObjectMapper mapper = new ObjectMapper();
        final String result = mapper.writeValueAsString(event);
        assertThat(result, containsString(toParse));
    }

    @Test
    public void whenUsingCustomDateSerializer_thenCorrect() throws JsonProcessingException, ParseException {
        final SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

        final String toParse = "20-12-2014 02:30:00";
        final Date date = df.parse(toParse);
        final EventWithSerializer event = new EventWithSerializer("party", date);

        final ObjectMapper mapper = new ObjectMapper();
        final String result = mapper.writeValueAsString(event);
        assertThat(result, containsString(toParse));
    }

    @Test
    public void whenSerializingJodaTimeWithJackson_thenCorrect() throws JsonProcessingException {
        final DateTime date = new DateTime(2014, 12, 20, 2, 30);
        final EventWithJodaTime event = new EventWithJodaTime("party", date);

        final ObjectMapper mapper = new ObjectMapper();
        final String result = mapper.writeValueAsString(event);
        assertThat(result, containsString("2014-12-20 02:30"));
    }

    @Test
    public void whenSerializingJava8DateWithCustomSerializer_thenCorrect() throws JsonProcessingException {
        final LocalDateTime date = LocalDateTime.of(2014, 12, 20, 2, 30);
        final EventWithLocalDateTime event = new EventWithLocalDateTime("party", date);

        final ObjectMapper mapper = new ObjectMapper();
        final String result = mapper.writeValueAsString(event);
        assertThat(result, containsString("2014-12-20 02:30"));
    }

    @Test
    public void whenDeserializingDateWithJackson_thenCorrect() throws IOException {
        final String json = "{\"name\":\"party\",\"eventDate\":\"20-12-2014 02:30:00\"}";

        final SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        final ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(df);

        final Event event = mapper.readerFor(Event.class)
            .readValue(json);
        assertEquals("20-12-2014 02:30:00", df.format(event.eventDate));
    }

    @Test
    public void whenDeserializingDateUsingCustomDeserializer_thenCorrect() throws IOException {
        final String json = "{\"name\":\"party\",\"eventDate\":\"20-12-2014 02:30:00\"}";

        final SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        final ObjectMapper mapper = new ObjectMapper();

        final EventWithSerializer event = mapper.readerFor(EventWithSerializer.class)
            .readValue(json);
        assertEquals("20-12-2014 02:30:00", df.format(event.eventDate));
    }

    @Test
    public void whenSerializingJava8Date_thenCorrect() throws JsonProcessingException {
        final LocalDateTime date = LocalDateTime.of(2014, 12, 20, 2, 30);

        final ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        final String result = mapper.writeValueAsString(date);
        assertThat(result, containsString("2014-12-20T02:30"));
    }

    @Test
    public void whenSerializingJava8DateAndReadingValue_thenCorrect() throws IOException {
        String stringDate = "\"2014-12-20\"";

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        LocalDate result = mapper.readValue(stringDate, LocalDate.class);
        assertThat(result.toString(), containsString("2014-12-20"));
    }

    @Test
    public void whenSerializingJava8DateAndReadingFromEntity_thenCorrect() throws IOException {
        String json = "{\"name\":\"party\",\"eventDate\":\"20-12-2014\"}";

        ObjectMapper mapper = new ObjectMapper();

        EventWithLocalDate result = mapper.readValue(json, EventWithLocalDate.class);
        assertThat(result.getEventDate().toString(), containsString("2014-12-20"));
    }

    @Test
    public void whenSerializingJodaTime_thenCorrect() throws JsonProcessingException {
        final DateTime date = new DateTime(2014, 12, 20, 2, 30, DateTimeZone.forID("Europe/London"));

        final ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JodaModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        final String result = mapper.writeValueAsString(date);
        assertThat(result, containsString("2014-12-20T02:30:00.000Z"));
    }

}