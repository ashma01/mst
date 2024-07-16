package com.software.testing.mst;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class KruskalMSTTest {

    private KruskalMST kruskalMST;

    @BeforeEach
    public void setUp() {
        kruskalMST = new KruskalMST();
        kruskalMST.numVertices = 4;
        kruskalMST.edges = new ArrayList<>();
        kruskalMST.edges.add(new Edge(0, 1, 10));
        kruskalMST.edges.add(new Edge(0, 2, 6));
        kruskalMST.edges.add(new Edge(0, 3, 5));
        kruskalMST.edges.add(new Edge(1, 3, 15));
        kruskalMST.edges.add(new Edge(2, 3, 4));
    }

    @Test
    public void testKruskalAlgo() {
        List<Edge> mst = kruskalMST.kruskalAlgo();
        assertNotNull(mst);
        assertEquals(3, mst.size()); // The MST for 4 vertices will have 3 edges

        int totalWeight = kruskalMST.printMST(mst);
        assertEquals(21, totalWeight); // Correct MST weight based on the provided edges
    }

    @Test
    public void testPrintMST() {
        List<Edge> mst = new ArrayList<>();
        mst.add(new Edge(0, 1, 10));
        mst.add(new Edge(0, 2, 6));
        mst.add(new Edge(2, 3, 4));

        int totalWeight = kruskalMST.printMST(mst);
        assertEquals(20, totalWeight); // 10 + 6 + 4
    }

    @Test
    public void testEmptyGraph() {
        kruskalMST.numVertices = 0;
        List<Edge> mst = kruskalMST.kruskalAlgo();
        assertTrue(mst.isEmpty());
    }

    @Test
    public void testSingleVertexGraph() {
        kruskalMST.numVertices = 1;
        List<Edge> mst = kruskalMST.kruskalAlgo();
        assertTrue(mst.isEmpty());
    }
}
