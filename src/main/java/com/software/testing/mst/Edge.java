package com.software.testing.mst;

import java.util.List;

public class Edge implements Comparable{
    int from;
    int to;
    int weight;

    Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}