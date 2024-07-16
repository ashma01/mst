package com.software.testing.mst;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EdgeTest {

    @Test
    public void testEdgeCreation() {
        Edge edge = new Edge(0, 1, 10);
        assertEquals(0, edge.from);
        assertEquals(1, edge.to);
        assertEquals(10, edge.weight);
    }

    @Test
    public void testEdgeEquality() {
        Edge edge1 = new Edge(0, 1, 10);
        Edge edge2 = new Edge(0, 1, 10);
        Edge edge3 = new Edge(1, 0, 10);
        assertEquals(edge1, edge2);
        assertNotEquals(edge1, edge3);
    }

    @Test
    public void testEdgeHashCode() {
        Edge edge1 = new Edge(0, 1, 10);
        Edge edge2 = new Edge(0, 1, 10);
        assertEquals(edge1.hashCode(), edge2.hashCode());
    }

    @Test
    public void testEdgeCompareTo() {
        Edge edge1 = new Edge(0, 1, 10);
        Edge edge2 = new Edge(0, 1, 10);
        assertEquals(0, edge1.compareTo(edge2));
    }

    @Test
    public void testEdgeWithNull() {
        Edge edge = new Edge(0, 1, 10);
        assertNotEquals(edge, null);
    }

    @Test
    public void testEdgeWithDifferentClass() {
        Edge edge = new Edge(0, 1, 10);
        assertNotEquals(edge, new Object());
    }
}
