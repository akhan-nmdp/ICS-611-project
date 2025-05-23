package com.baeldung.algorithms.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Graph {

    private Map<Integer, List<Integer>> adjVertices;

    public Graph() {
        this.adjVertices = new HashMap<Integer, List<Integer>>();
    }

    public void addVertex(int vertex) {
        adjVertices.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(int src, int dest) {
        adjVertices.get(src).add(dest);
    }

    public boolean[] dfsWithoutRecursion(int start) {
        Stack<Integer> stack = new Stack<Integer>();
        boolean[] isVisited = new boolean[adjVertices.size()];
        stack.push(start);
        while (!stack.isEmpty()) {
            int current = stack.pop();
            if(!isVisited[current]){
                isVisited[current] = true;
                visit(current);
                for (int dest : adjVertices.get(current)) {
                    if (!isVisited[dest])
                        stack.push(dest);
                }
            }
        }
        return isVisited;
    }

    public boolean[] dfs(int start) {
        boolean[] isVisited = new boolean[adjVertices.size()];
        return dfsRecursive(start, isVisited);
    }

    private boolean[] dfsRecursive(int current, boolean[] isVisited) {
        isVisited[current] = true;
        visit(current);
        for (int dest : adjVertices.get(current)) {
            if (!isVisited[dest])
                dfsRecursive(dest, isVisited);
        }
        return isVisited;
    }

    public List<Integer> topologicalSort(int start) {
        LinkedList<Integer> result = new LinkedList<Integer>();
        boolean[] isVisited = new boolean[adjVertices.size()];
        topologicalSortRecursive(start, isVisited, result);
        return result;
    }

    private void topologicalSortRecursive(int current, boolean[] isVisited, LinkedList<Integer> result) {
        isVisited[current] = true;
        for (int dest : adjVertices.get(current)) {
            if (!isVisited[dest])
                topologicalSortRecursive(dest, isVisited, result);
        }
        result.addFirst(current);
    }

    private void visit(int value) {
        System.out.print(" " + value);        
    }
}
