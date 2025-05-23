package com.baeldung.streams.mapstreamtomap;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Math.max;
import static java.util.stream.Collectors.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MapStreamToMapUnitTest {
    private static MergeMaps mergeMaps;

    @BeforeClass
    public static void setup() {
        mergeMaps = new MergeMaps();
    }

    Map<String, Integer> playerMap1 = new HashMap<String, Integer>() {{
        put("Kai", 92);
        put("Liam", 100);
    }};
    Map<String, Integer> playerMap2 = new HashMap<String, Integer>() {{
        put("Eric", 42);
        put("Kevin", 77);
    }};
    Map<String, Integer> playerMap3 = new HashMap<String, Integer>() {{
        put("Saajan", 35);
    }};
    Map<String, Integer> playerMap4 = new HashMap<String, Integer>() {{
        put("Kai", 76);
    }};
    Map<String, Integer> playerMap5 = new HashMap<String, Integer>() {{
        put("Kai", null);
        put("Jerry", null);
    }};

    @Test
    void givenMapsStream_whenUsingFlatMapAndToMap_thenMultipleMapsMergedIntoOneMap() {

        Map<String, Integer> expectedMap = new HashMap<String, Integer>() {{
            put("Saajan", 35);
            put("Liam", 100);
            put("Kai", 92);
            put("Eric", 42);
            put("Kevin", 77);
        }};

        Map<String, Integer> mergedMap = Stream.of(playerMap1, playerMap2, playerMap3)
            .flatMap(map -> map.entrySet()
                .stream())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        assertEquals(expectedMap, mergedMap);
    }

    @Test
    void givenMapsWithDuplicateKeys_whenUsingFlatMapAndToMap_thenMultipleMapsMergedIntoOneMap() {

        Map<String, Integer> expectedMap = new HashMap<String, Integer>() {{
            put("Saajan", 35);
            put("Liam", 100);
            put("Kai", 92); // max of 76 and 92
            put("Eric", 42);
            put("Kevin", 77);
        }};

        assertThrows(IllegalStateException.class, () -> Stream.of(playerMap1, playerMap2, playerMap3, playerMap4)
            .flatMap(map -> map.entrySet()
                .stream())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)), "Duplicate key Kai (attempted merging values 92 and 76)");

        Map<String, Integer> mergedMap = Stream.of(playerMap1, playerMap2, playerMap3, playerMap4)
            .flatMap(map -> map.entrySet()
                .stream())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Integer::max));

        assertEquals(expectedMap, mergedMap);
    }

    private Integer maxInteger(Integer int1, Integer int2) {
        if (int1 == null) {
            return int2;
        }
        if (int2 == null) {
            return int1;
        }
        return max(int1, int2);
    }

    @Test
    void givenMapsWithDuplicateKeysAndNullValues_whenUsingFlatMapWithForEach_thenMultipleMapsMergedIntoOneMap() {

        Map<String, Integer> expectedMap = new HashMap<String, Integer>() {{
            put("Saajan", 35);
            put("Liam", 100);
            put("Kai", 92); // max of 92, 76, and null
            put("Eric", 42);
            put("Kevin", 77);
            put("Jerry", null);
        }};

        assertThrows(NullPointerException.class, () -> Stream.of(playerMap1, playerMap2, playerMap3, playerMap4, playerMap5)
            .flatMap(map -> map.entrySet()
                .stream())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Integer::max)));

        Map<String, Integer> mergedMap = new HashMap<>();
        Stream.of(playerMap1, playerMap2, playerMap3, playerMap4, playerMap5)
            .flatMap(map -> map.entrySet()
                .stream())
            .forEach(entry -> {
                String k = entry.getKey();
                Integer v = entry.getValue();
                if (mergedMap.containsKey(k)) {
                    mergedMap.put(k, maxInteger(mergedMap.get(k), v));
                } else {
                    mergedMap.put(k, v);
                }
            });
        assertEquals(expectedMap, mergedMap);

    }

    @Test
    void givenMapsWithDuplicateKeysAndNullValues_whenUsingReduce_thenMultipleMapsMergedIntoOneMap() {

        Map<String, Integer> expectedMap = new HashMap<String, Integer>() {{
            put("Saajan", 35);
            put("Liam", 100);
            put("Kai", 92); // max of 92, 76, and null
            put("Eric", 42);
            put("Kevin", 77);
            put("Jerry", null);
        }};
        Map<String, Integer> mergedMap = Stream.of(playerMap1, playerMap2, playerMap3, playerMap4, playerMap5)
            .flatMap(x -> x.entrySet()
                .stream())
            .collect(groupingBy(Map.Entry::getKey, mapping(Map.Entry::getValue, reducing(null, this::maxInteger))));
        assertEquals(expectedMap, mergedMap);
    }

    @Test
    public void givenListOfMaps_whenConvertingToSingleMap_thenResultIsSingleMergedMap() {
        List<Map<String, Integer>> listOfMaps = new ArrayList<>();
        listOfMaps.add(playerMap1);
        listOfMaps.add(playerMap2);

        Map<String, Integer> expected = new HashMap<>();
        expected.put("Kai", 92);
        expected.put("Liam", 100);
        expected.put("Eric", 42);
        expected.put("Kevin", 77);

        Map<String, Integer> result = mergeMaps.mergeMapsUsingLoop(listOfMaps);
        assertEquals(expected, result);
    }

    @Test
    public void givenListOfMaps_whenConvertingToSingleMapUsingStream_thenResultIsSingleMergedMap() {
        List<Map<String, Integer>> listOfMaps = new ArrayList<>();
        listOfMaps.add(playerMap1);
        listOfMaps.add(playerMap2);

        Map<String, Integer> expected = new HashMap<>();
        expected.put("Kai", 92);
        expected.put("Liam", 100);
        expected.put("Eric", 42);
        expected.put("Kevin", 77);

        Map<String, Integer> result = mergeMaps.mergeMapsUsingStream(listOfMaps);
        assertEquals(expected, result);
    }
}