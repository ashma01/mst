package com.software.testing.mst;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BoruvkasMSTTest {

    private BoruvkasMST boruvkasMST;

    @BeforeEach
    public void setUp() {
        boruvkasMST = new BoruvkasMST();
        boruvkasMST.numVertices = 4;
        boruvkasMST.edges = new ArrayList<>();
        boruvkasMST.edges.add(new Edge(0, 1, 10));
        boruvkasMST.edges.add(new Edge(0, 2, 6));
        boruvkasMST.edges.add(new Edge(0, 3, 5));
        boruvkasMST.edges.add(new Edge(1, 3, 15));
        boruvkasMST.edges.add(new Edge(2, 3, 4));
    }

    @Test
    public void testBoruvkaMST() {
        int mstWeight = boruvkasMST.boruvkaMST();
        assertEquals(19, mstWeight); // Adjust this value based on the correct MST calculation
    }

    @Test
    public void testEmptyGraph() {
        boruvkasMST.numVertices = 0;
        int mstWeight = boruvkasMST.boruvkaMST();
        assertEquals(0, mstWeight);
    }

    @Test
    public void testSingleVertexGraph() {
        boruvkasMST.numVertices = 1;
        int mstWeight = boruvkasMST.boruvkaMST();
        assertEquals(0, mstWeight);
    }
}
