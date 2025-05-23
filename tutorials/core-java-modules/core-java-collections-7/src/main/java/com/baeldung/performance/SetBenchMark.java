package com.baeldung.performance;

import java.util.LinkedHashSet;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 10)
public class SetBenchMark {

    @State(Scope.Thread)
    public static class MyState {

        //Set<Employee> employeeSet = new HashSet<>();
        LinkedHashSet<Employee> employeeSet = new LinkedHashSet<>();
        //ConcurrentSkipListSet<Employee> employeeSet = new ConcurrentSkipListSet <>();

        // TreeSet 

        long iterations = 1000;
        Employee employee = new Employee(100L, "Harry");

        @Setup(Level.Trial)
        public void setUp() {
            for (long i = 0; i < iterations; i++) {
                employeeSet.add(new Employee(i, "John"));
            }

            //employeeSet.add(employee);
        }
    }

    @Benchmark
    public boolean testAdd(MyState state) {
        return state.employeeSet.add(state.employee);
    }

    @Benchmark
    public Boolean testContains(MyState state) {
        return state.employeeSet.contains(state.employee);
    }

    @Benchmark
    public boolean testRemove(MyState state) {
        return state.employeeSet.remove(state.employee);
    }


    public static void main(String[] args) throws Exception {
        Options options = new OptionsBuilder()
                .include(SetBenchMark.class.getSimpleName()).threads(1)
                .forks(1).shouldFailOnError(true)
                .shouldDoGC(true)
                .jvmArgs("-server").build();
        new Runner(options).run();
    }
}
