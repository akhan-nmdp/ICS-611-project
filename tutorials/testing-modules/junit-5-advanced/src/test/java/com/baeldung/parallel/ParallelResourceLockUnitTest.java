package com.baeldung.parallel;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.ResourceLock;

import java.util.ArrayList;
import java.util.List;

public class ParallelResourceLockUnitTest {

    private List<String> resources;

    @BeforeEach
    void before() {
        resources = new ArrayList<>();
        resources.add("test");
    }

    @AfterEach
    void after() {
        resources.clear();
    }

    @Test
    @ResourceLock(value = "resources")
    public void first() throws Exception {
        System.out.println("ParallelResourceLockUnitTest first() start => " + Thread.currentThread().getName());
        resources.add("first");
        System.out.println(resources);
        Thread.sleep(500);
        System.out.println("ParallelResourceLockUnitTest first() end => " + Thread.currentThread().getName());
    }

    @Test
    @ResourceLock(value = "resources")
    public void second() throws Exception {
        System.out.println("ParallelResourceLockUnitTest second() start => " + Thread.currentThread().getName());
        resources.add("second");
        System.out.println(resources);
        Thread.sleep(500);
        System.out.println("ParallelResourceLockUnitTest second() end => " + Thread.currentThread().getName());
    }
}
