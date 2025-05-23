package com.baeldung.mapdb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;

public class InMemoryModesUnitTest {

    @Test
    public void givenDBCreatedOnHeap_whenUsed_checkUsageCorrect() {

        DB heapDB = DBMaker.heapDB().make();

        HTreeMap<Integer, String> map = heapDB
          .hashMap("myMap")
          .keySerializer(Serializer.INTEGER)
          .valueSerializer(Serializer.STRING)
          .createOrOpen();

        map.put(1, "ONE");

        assertEquals("ONE", map.get(1));

    }

    @Test
    public void givenDBCreatedBaseOnByteArray_whenUsed_checkUsageCorrect() {

        DB heapDB = DBMaker.memoryDB().make();

        HTreeMap<Integer, String> map = heapDB
          .hashMap("myMap")
          .keySerializer(Serializer.INTEGER)
          .valueSerializer(Serializer.STRING)
          .createOrOpen();

        map.put(1, "ONE");

        assertEquals("ONE", map.get(1));
    }

    @Test
    public void givenDBCreatedBaseOnDirectByteBuffer_whenUsed_checkUsageCorrect() {

        DB heapDB = DBMaker.memoryDirectDB().make();

        HTreeMap<Integer, String> map = heapDB
          .hashMap("myMap")
          .keySerializer(Serializer.INTEGER)
          .valueSerializer(Serializer.STRING)
          .createOrOpen();

        map.put(1, "ONE");

        assertEquals("ONE", map.get(1));
    }

}
