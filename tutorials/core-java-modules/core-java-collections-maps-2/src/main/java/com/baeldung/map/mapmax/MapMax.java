package com.baeldung.map.mapmax;

import java.util.*;
import java.util.Map.Entry;

public class MapMax {

    public <K, V extends Comparable<V>> V maxUsingIteration(Map<K, V> map) {

        Entry<K, V> maxEntry = null;

        for (Entry<K, V> entry : map.entrySet()) {

            if (maxEntry == null || entry.getValue()
                .compareTo(maxEntry.getValue()) > 0) {
                maxEntry = entry;
            }
        }

        return maxEntry.getValue();
    }

    public <K, V extends Comparable<V>> V maxUsingCollectionsMax(Map<K, V> map) {

        Entry<K, V> maxEntry = Collections.max(map.entrySet(), new Comparator<Entry<K, V>>() {
            public int compare(Entry<K, V> e1, Entry<K, V> e2) {
                return e1.getValue()
                    .compareTo(e2.getValue());
            }
        });

        return maxEntry.getValue();
    }

    public <K, V extends Comparable<V>> V maxUsingCollectionsMaxAndLambda(Map<K, V> map) {

        Entry<K, V> maxEntry = Collections.max(map.entrySet(), (Entry<K, V> e1, Entry<K, V> e2) -> e1.getValue()
            .compareTo(e2.getValue()));

        return maxEntry.getValue();
    }

    public <K, V extends Comparable<V>> V maxUsingCollectionsMaxAndMethodReference(Map<K, V> map) {

        Entry<K, V> maxEntry = Collections.max(map.entrySet(), Comparator.comparing(Entry::getValue));

        return maxEntry.getValue();
    }

    public <K, V extends Comparable<V>> V maxUsingStreamAndLambda(Map<K, V> map) {

        Optional<Entry<K, V>> maxEntry = map.entrySet()
            .stream()
            .max((Entry<K, V> e1, Entry<K, V> e2) -> e1.getValue()
                .compareTo(e2.getValue()));

        return maxEntry.get()
            .getValue();
    }

    public <K, V extends Comparable<V>> V maxUsingStreamAndMethodReference(Map<K, V> map) {

        Optional<Entry<K, V>> maxEntry = map.entrySet()
            .stream()
            .max(Comparator.comparing(Entry::getValue));

        return maxEntry.get()
            .getValue();
    }

    public <K, V extends Comparable<V>> K keyOfMaxUsingStream(Map<K, V> map) {
        return map.entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse(null);
    }

    public static void main(String[] args) {

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        map.put(1, 3);
        map.put(2, 4);
        map.put(3, 5);
        map.put(4, 6);
        map.put(5, 7);
        
        MapMax mapMax = new MapMax();

        System.out.println(mapMax.maxUsingIteration(map));
        System.out.println(mapMax.maxUsingCollectionsMax(map));
        System.out.println(mapMax.maxUsingCollectionsMaxAndLambda(map));
        System.out.println(mapMax.maxUsingCollectionsMaxAndMethodReference(map));
        System.out.println(mapMax.maxUsingStreamAndLambda(map));
        System.out.println(mapMax.maxUsingStreamAndMethodReference(map));

    }

}
