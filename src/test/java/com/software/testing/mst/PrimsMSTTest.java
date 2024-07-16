package com.software.testing.mst;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PrimsMSTTest {

    private PrimsMST primsMST;

    @BeforeEach
    public void setUp() {
        primsMST = new PrimsMST(3);
    }

    @Test
    public void testPrimsMSTInitialization() {
        PrimsMST emptyMST = new PrimsMST(0);
        assertEquals(0, emptyMST.numVertices);
        assertTrue(emptyMST.adjList.isEmpty());
        assertEquals(0, emptyMST.minEdgeWeight.length);
        assertEquals(0, emptyMST.parent.length);
        assertTrue(emptyMST.minHeap.isEmpty());
    }

    @Test
    public void testAddEdge() {
        primsMST.addEdge(0, 1, 10);
        assertEquals(1, primsMST.adjList.get(0).size());
        assertEquals(1, primsMST.adjList.get(1).size());
        assertEquals(0, primsMST.adjList.get(2).size());

        Edge edge = primsMST.adjList.get(0).get(0);
        assertEquals(0, edge.from);
        assertEquals(1, edge.to);
        assertEquals(10, edge.weight);
    }

    @Test
    public void testGetMST() {
        primsMST.addEdge(0, 1, 10);
        primsMST.addEdge(1, 2, 15);
        primsMST.addEdge(0, 2, 5);

        primsMST.getMST();
        assertTrue(primsMST.inMST.contains(0));
        assertTrue(primsMST.inMST.contains(1));
        assertTrue(primsMST.inMST.contains(2));
    }

    @Test
    public void testPrintMST() {
        primsMST.addEdge(0, 1, 10);
        primsMST.addEdge(1, 2, 15);
        primsMST.addEdge(0, 2, 5);

        primsMST.getMST();
        int totalWeight = primsMST.printMST();
        assertEquals(15, totalWeight); // Adjust this value based on the correct MST calculation
    }

    @Test
    public void testEmptyGraph() {
        PrimsMST emptyMST = new PrimsMST(0);
        emptyMST.getMST();
        int totalWeight = emptyMST.printMST();
        assertEquals(0, totalWeight);
    }

    @Test
    public void testSingleVertexGraph() {
        PrimsMST singleVertexMST = new PrimsMST(1);
        singleVertexMST.getMST();
        int totalWeight = singleVertexMST.printMST();
        assertEquals(0, totalWeight);
    }
}
