package com.software.testing.mst;

import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class GraphReaderTest {

    @Test
    public void testReadPrimGraph() throws IOException {
        String filePath = "test_prim_graph.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("3\n");
            writer.write("0 1 10\n");
            writer.write("1 2 15\n");
            writer.write("2 0 5\n");
        }

        GraphReader graphReader = new GraphReader();
        PrimsMST primsMST = graphReader.readPrimGraph(filePath);

        assertNotNull(primsMST);
        // Add more assertions based on the expected structure of PrimsMST

        Files.delete(Path.of(filePath));
    }

    @Test
    public void testReadKruskalGraph() throws IOException {
        String filePath = "test_kruskal_graph.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("3\n");
            writer.write("0 1 10\n");
            writer.write("1 2 15\n");
            writer.write("2 0 5\n");
        }

        GraphReader graphReader = new GraphReader();
        KruskalMST kruskalMST = graphReader.readKruskalGraph(filePath);

        assertNotNull(kruskalMST);
        assertEquals(3, kruskalMST.numVertices);
        assertEquals(3, kruskalMST.edges.size());
        // Add more assertions based on the expected structure of KruskalMST

        Files.delete(Path.of(filePath));
    }

    @Test
    public void testReadBoruvkasGraph() throws IOException {
        String filePath = "test_boruvkas_graph.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("3\n");
            writer.write("0 1 10\n");
            writer.write("1 2 15\n");
            writer.write("2 0 5\n");
        }

        GraphReader graphReader = new GraphReader();
        BoruvkasMST boruvkasMST = graphReader.readBoruvkasGraph(filePath);

        assertNotNull(boruvkasMST);
        assertEquals(3, boruvkasMST.numVertices);
        assertEquals(3, boruvkasMST.edges.size());
        // Add more assertions based on the expected structure of BoruvkasMST

        Files.delete(Path.of(filePath));
    }

    @Test
    public void testReadGraphFileNotFound() {
        GraphReader graphReader = new GraphReader();
        PrimsMST primsMST = graphReader.readPrimGraph("non_existent_file.txt");
        assertNotNull(primsMST);
        // Ensure that no edges are read
    }
}
