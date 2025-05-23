package com.baeldung.powerset;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.hamcrest.collection.IsCollectionWithSize;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

public class PowerSetUtilityUnitTest {

    @Test
    public void givenSet_WhenGuavaLibraryGeneratePowerSet_ThenItContainsAllSubsets() {
        ImmutableSet<String> set = ImmutableSet.of("APPLE", "ORANGE", "MANGO");
        Set<Set<String>> powerSet = Sets.powerSet(set);
        Assertions.assertEquals((1 << set.size()), powerSet.size());
        MatcherAssert.assertThat(powerSet, Matchers.containsInAnyOrder(
          ImmutableSet.of(),
          ImmutableSet.of("APPLE"),
          ImmutableSet.of("ORANGE"),
          ImmutableSet.of("APPLE", "ORANGE"),
          ImmutableSet.of("MANGO"),
          ImmutableSet.of("APPLE", "MANGO"),
          ImmutableSet.of("ORANGE", "MANGO"),
          ImmutableSet.of("APPLE", "ORANGE", "MANGO")
        ));
    }

    @Test
    public void givenSet_WhenPowerSetIsLazyLoadGenerated_ThenItContainsAllSubsets() {
        Set<String> set = RandomSetOfStringGenerator.generateRandomSet();
        Set<Set<String>> powerSet = new PowerSetUtility<String>().lazyLoadPowerSet(set);

        //To make sure that the size of power set is (2 power n)
        MatcherAssert.assertThat(powerSet, IsCollectionWithSize.hasSize((1 << set.size())));
        //To make sure that number of occurrence of each element is (2 power n-1)
        Map<String, Integer> counter = new HashMap<>();
        for (Set<String> subset : powerSet) {
            for (String name : subset) {
                int num = counter.getOrDefault(name, 0);
                counter.put(name, num + 1);
            }
        }
        counter.forEach((k, v) -> Assertions.assertEquals((1 << (set.size() - 1)), v.intValue()));
    }

    @Test
    public void givenSet_WhenPowerSetIsCalculated_ThenItContainsAllSubsets() {
        Set<String> set = RandomSetOfStringGenerator.generateRandomSet();

        Set<Set<String>> powerSet = new PowerSetUtility<String>().recursivePowerSet(set);

        //To make sure that the size of power set is (2 power n)
        MatcherAssert.assertThat(powerSet, IsCollectionWithSize.hasSize((1 << set.size())));
        //To make sure that number of occurrence of each element is (2 power n-1)
        Map<String, Integer> counter = new HashMap<>();
        for (Set<String> subset : powerSet) {
            for (String name : subset) {
                int num = counter.getOrDefault(name, 0);
                counter.put(name, num + 1);
            }
        }
        counter.forEach((k, v) -> Assertions.assertEquals((1 << (set.size() - 1)), v.intValue()));
    }

    @Test
    public void givenSet_WhenPowerSetIsCalculatedRecursiveByIndexRepresentation_ThenItContainsAllSubsets() {
        Set<String> set = RandomSetOfStringGenerator.generateRandomSet();

        Set<Set<String>> powerSet = new PowerSetUtility<String>().recursivePowerSetIndexRepresentation(set);

        //To make sure that the size of power set is (2 power n)
        MatcherAssert.assertThat(powerSet, IsCollectionWithSize.hasSize((1 << set.size())));
        //To make sure that number of occurrence of each element is (2 power n-1)
        Map<String, Integer> counter = new HashMap<>();
        for (Set<String> subset : powerSet) {
            for (String name : subset) {
                int num = counter.getOrDefault(name, 0);
                counter.put(name, num + 1);
            }
        }
        counter.forEach((k, v) -> Assertions.assertEquals((1 << (set.size() - 1)), v.intValue()));
    }

    @Test
    public void givenSet_WhenPowerSetIsCalculatedRecursiveByBinaryRepresentation_ThenItContainsAllSubsets() {
        Set<String> set = RandomSetOfStringGenerator.generateRandomSet();

        Set<Set<String>> powerSet = new PowerSetUtility<String>().recursivePowerSetBinaryRepresentation(set);

        //To make sure that the size of power set is (2 power n)
        MatcherAssert.assertThat(powerSet, IsCollectionWithSize.hasSize((1 << set.size())));
        //To make sure that number of occurrence of each element is (2 power n-1)
        Map<String, Integer> counter = new HashMap<>();
        for (Set<String> subset : powerSet) {
            for (String name : subset) {
                int num = counter.getOrDefault(name, 0);
                counter.put(name, num + 1);
            }
        }
        counter.forEach((k, v) -> Assertions.assertEquals((1 << (set.size() - 1)), v.intValue()));
    }

    @Test
    public void givenSet_WhenPowerSetIsCalculatedIterativePowerSetByLoopOverNumbers_ThenItContainsAllSubsets() {
        Set<String> set = RandomSetOfStringGenerator.generateRandomSet();

        List<List<String>> powerSet = new PowerSetUtility<String>().iterativePowerSetByLoopOverNumbers(set);

        //To make sure that the size of power set is (2 power n)
        MatcherAssert.assertThat(powerSet, IsCollectionWithSize.hasSize((1 << set.size())));
        //To make sure that number of occurrence of each element is (2 power n-1)
        Map<String, Integer> counter = new HashMap<>();
        for (List<String> subset : powerSet) {
            for (String name : subset) {
                int num = counter.getOrDefault(name, 0);
                counter.put(name, num + 1);
            }
        }
        counter.forEach((k, v) -> Assertions.assertEquals((1 << (set.size() - 1)), v.intValue()));
        //To make sure that one subset is not generated twice
        Assertions.assertEquals(powerSet.size(), new HashSet<>(powerSet).size());
        //To make sure that each element in each subset is occurred once
        for (List<String> subset : powerSet) {
            Assertions.assertEquals(subset.size(), new HashSet<>(subset).size());
        }
    }

    @Test
    public void givenSet_WhenPowerSetIsCalculatedIterativePowerSetByLoopOverNumbersWithMinimalChange_ThenItContainsAllSubsets() {

        Set<String> set = RandomSetOfStringGenerator.generateRandomSet();
        List<List<String>> powerSet = new PowerSetUtility<String>().iterativePowerSetByLoopOverNumbersMinimalChange(set);

        //To make sure that the size of power set is (2 power n)
        MatcherAssert.assertThat(powerSet, IsCollectionWithSize.hasSize((1 << set.size())));
        //To make sure that number of occurrence of each element is (2 power n-1)
        Map<String, Integer> counter = new HashMap<>();
        for (List<String> subset : powerSet) {
            for (String name : subset) {
                int num = counter.getOrDefault(name, 0);
                counter.put(name, num + 1);
            }
        }
        counter.forEach((k, v) -> Assertions.assertEquals((1 << (set.size() - 1)), v.intValue()));
        //To make sure that one subset is not generated twice
        Assertions.assertEquals(powerSet.size(), new HashSet<>(powerSet).size());
        //To make sure that each element in each subset is occurred once
        for (List<String> subset : powerSet) {
            Assertions.assertEquals(subset.size(), new HashSet<>(subset).size());
        }
        //To make sure that difference of consecutive subsets is exactly 1
        for(int i=1; i<powerSet.size(); i++) {
            int diff = 0;
            for (String s : powerSet.get(i - 1))
                if (!powerSet.get(i).contains(s))
                    diff++;
            for (String s : powerSet.get(i))
                if (!powerSet.get(i - 1).contains(s))
                    diff++;
            Assertions.assertEquals(1, diff);
        }
    }

    static class RandomSetOfStringGenerator {
        private static List<String> fruits = Arrays.asList("Apples", "Avocados", "Banana", "Blueberry", "Cherry", "Clementine", "Cucumber", "Date", "Fig",
          "Grapefruit"/*, "Grape", "Kiwi", "Lemon", "Mango", "Mulberry", "Melon", "Nectarine", "Olive", "Orange"*/);

        static Set<String> generateRandomSet() {
            Set<String> set = new HashSet<>();
            Random random = new Random();
            int size = random.nextInt(fruits.size());
            while (set.size() != size) {
                set.add(fruits.get(random.nextInt(fruits.size())));
            }
            return set;
        }
    }
}
