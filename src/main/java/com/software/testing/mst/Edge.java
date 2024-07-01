package com.software.testing.mst;

import java.util.List;
import java.util.Objects;

public class Edge implements Comparable{
    int from;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge edge)) return false;
        return from == edge.from && to == edge.to && weight == edge.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, weight);
    }

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