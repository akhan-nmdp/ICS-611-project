package com.baeldung.jgrapht;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.IntStream;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.cycle.HierholzerEulerianCycle;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EulerianCircuitUnitTest {
    SimpleWeightedGraph<String, DefaultEdge> simpleGraph;

    @BeforeEach
    public void createGraphWithEulerianCircuit() {
        simpleGraph = new SimpleWeightedGraph<>(DefaultEdge.class);
        IntStream.range(1, 6).forEach(i -> {
            simpleGraph.addVertex("v" + i);
        });
        IntStream.range(1, 6).forEach(i -> {
            int endVertexNo = (i + 1) > 5 ? 1 : i + 1;
            simpleGraph.addEdge("v" + i, "v" + endVertexNo);
        });
    }

    @Test
    public void givenGraph_whenCheckEluerianCycle_thenGetResult() {
        HierholzerEulerianCycle eulerianCycle = new HierholzerEulerianCycle<>();
        assertTrue(eulerianCycle.isEulerian(simpleGraph));
    }

    @Test
    public void givenGraphWithEulerianCircuit_whenGetEulerianCycle_thenGetGraphPath() {
        HierholzerEulerianCycle eulerianCycle = new HierholzerEulerianCycle<>();
        GraphPath path = eulerianCycle.getEulerianCycle(simpleGraph);
        assertTrue(path.getEdgeList().containsAll(simpleGraph.edgeSet()));
    }
}
