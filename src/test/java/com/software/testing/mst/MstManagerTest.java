package com.software.testing.mst;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class MstManagerTest {

    private MstManager mstManager;
    private PrimsMST primsMST;
    private KruskalMST kruskalMST;
    private BoruvkasMST boruvkasMST;
    private GraphReader graphReader;

    @BeforeEach
    public void setUp() {
        primsMST = mock(PrimsMST.class);
        kruskalMST = mock(KruskalMST.class);
        boruvkasMST = mock(BoruvkasMST.class);
        graphReader = mock(GraphReader.class);
        mstManager = new MstManager(primsMST, kruskalMST, boruvkasMST, graphReader);
    }

    @Test
    public void testRunAlgoWithPrims() throws IOException {
        String filename = "testfile.txt";
        when(graphReader.readPrimGraph(filename)).thenReturn(primsMST);
        when(primsMST.printMST()).thenReturn(10);

        Response response = mstManager.runAlgo(filename, "prims");

        assertEquals("prims", response.getAlgo());
        assertEquals(10, response.getTotalWeight());
        assertTrue(response.getTimeTaken().endsWith("ms"));
    }

    @Test
    public void testRunAlgoWithKruskals() throws IOException {
        String filename = "testfile.txt";
        when(graphReader.readKruskalGraph(filename)).thenReturn(kruskalMST);
        when(kruskalMST.printMST(anyList())).thenReturn(10);

        Response response = mstManager.runAlgo(filename, "kruskals");

        assertEquals("kruskals", response.getAlgo());
        assertEquals(10, response.getTotalWeight());
        assertTrue(response.getTimeTaken().endsWith("ms"));
    }

    @Test
    public void testRunAlgoWithBoruvkas() throws IOException {
        String filename = "testfile.txt";
        when(graphReader.readBoruvkasGraph(filename)).thenReturn(boruvkasMST);
        when(boruvkasMST.boruvkaMST()).thenReturn(10);

        Response response = mstManager.runAlgo(filename, "boruvkas");

        assertEquals("boruvkas", response.getAlgo());
        assertEquals(10, response.getTotalWeight());
        assertTrue(response.getTimeTaken().endsWith("ms"));
    }

    @Test
    public void testRunAlgoWithAll() throws IOException {
        String filename = "testfile.txt";
        when(graphReader.readPrimGraph(filename)).thenReturn(primsMST);
        when(primsMST.printMST()).thenReturn(10);
        when(graphReader.readKruskalGraph(filename)).thenReturn(kruskalMST);
        when(kruskalMST.printMST(anyList())).thenReturn(10);
        when(graphReader.readBoruvkasGraph(filename)).thenReturn(boruvkasMST);
        when(boruvkasMST.boruvkaMST()).thenReturn(10);

        Response response = mstManager.runAlgo(filename, "All");

        assertEquals("All", response.getAlgo());
        assertEquals(10, response.getTotalWeight());
        assertTrue(response.getTimeTaken().endsWith("ms"));
    }

    @Test
    public void testRunAlgoWithInvalidAlgo() throws IOException {
        String filename = "testfile.txt";

        Response response = mstManager.runAlgo(filename, "invalid");

        assertEquals("invalid", response.getAlgo());
        assertEquals(0, response.getTotalWeight());
        assertTrue(response.getTimeTaken().endsWith("ms"));
    }

    @Test
    public void testCallAllDifferentWeights() throws IOException {
        String filename = "testfile.txt";
        when(graphReader.readPrimGraph(filename)).thenReturn(primsMST);
        when(primsMST.printMST()).thenReturn(10);
        when(graphReader.readKruskalGraph(filename)).thenReturn(kruskalMST);
        when(kruskalMST.printMST(anyList())).thenReturn(20);
        when(graphReader.readBoruvkasGraph(filename)).thenReturn(boruvkasMST);
        when(boruvkasMST.boruvkaMST()).thenReturn(30);

        int weight = mstManager.runAlgo(filename, "All").getTotalWeight();
        assertEquals(0, weight); // When weights don't match
    }

    @Test
    public void testCallAllSameWeights() throws IOException {
        String filename = "testfile.txt";
        when(graphReader.readPrimGraph(filename)).thenReturn(primsMST);
        when(primsMST.printMST()).thenReturn(10);
        when(graphReader.readKruskalGraph(filename)).thenReturn(kruskalMST);
        when(kruskalMST.printMST(anyList())).thenReturn(10);
        when(graphReader.readBoruvkasGraph(filename)).thenReturn(boruvkasMST);
        when(boruvkasMST.boruvkaMST()).thenReturn(10);

        int weight = mstManager.runAlgo(filename, "All").getTotalWeight();
        assertEquals(10, weight); // When weights match
    }
}
