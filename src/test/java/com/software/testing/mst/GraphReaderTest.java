package com.software.testing.mst;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GraphReaderTest {

    @Autowired
    private GraphReader graphReader;

    private Path tempFile;

    @BeforeEach
    public void setUp() throws IOException {
        // Create a temporary file to simulate graph input
        tempFile = Files.createTempFile("graph", ".txt");

        // Write sample graph data to the temporary file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile.toFile()))) {
            writer.write("4\n"); // Number of vertices
            writer.write("0 1 10\n");
            writer.write("0 2 6\n");
            writer.write("0 3 5\n");
            writer.write("1 3 15\n");
            writer.write("2 3 4\n");
        }
    }

    @Test
    public void testReadPrimGraph() {
        PrimsMST primsMST = graphReader.readPrimGraph(tempFile.toString());

        // Verify the graph structure
        assertNotNull(primsMST, "PrimsMST should not be null");
        assertEquals(4, primsMST.numVertices, "Number of vertices should be 4");

//        // Verify edges
        assertTrue(primsMST.adjList.get(0).contains(new Edge(0, 1, 10)), "Edge 0-1 with weight 10 should be present");
        assertTrue(primsMST.adjList.get(0).contains(new Edge(0, 2, 6)), "Edge 0-2 with weight 6 should be present");
        assertTrue(primsMST.adjList.get(0).contains(new Edge(0, 3, 5)), "Edge 0-3 with weight 5 should be present");
        assertTrue(primsMST.adjList.get(1).contains(new Edge(1, 3, 15)), "Edge 1-3 with weight 15 should be present");
        assertTrue(primsMST.adjList.get(2).contains(new Edge(2, 3, 4)), "Edge 2-3 with weight 4 should be present");
    }

    @Test
    public void testReadPrimGraph_FileNotFound() {
        // Test with a non-existent file path
        PrimsMST primsMST = graphReader.readPrimGraph("non_existent_file.txt");
        assertNotNull(primsMST, "PrimsMST should not be null even if file is not found");
        assertEquals(0, primsMST.numVertices, "Number of vertices should be 0 when file is not found");
    }

    @AfterEach
    public void tearDown() throws IOException {
        // Delete the temporary file
        Files.deleteIfExists(tempFile);
    }
}

