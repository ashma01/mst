package com.software.testing.mst;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
@NoArgsConstructor
public class GraphReader {

    public PrimsMST readPrimGraph(String filePath) {
        PrimsMST primsMST = new PrimsMST();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Read the number of vertices from the first line
            int numVertices = Integer.parseInt(reader.readLine());
            primsMST = new PrimsMST(numVertices);
            primsMST = null;
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s+"); // Split line by whitespace
                int src = Integer.parseInt(parts[0]);
                int dest = Integer.parseInt(parts[1]);
                int weight = Integer.parseInt(parts[2]);

                primsMST.addEdge(src, dest, weight);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + filePath);
            e.printStackTrace();
        }
        return primsMST;
    }

    public KruskalMST readKruskalGraph(String filename) throws IOException {
        KruskalMST kruskalMST = new KruskalMST();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        int vertices = Integer.parseInt(reader.readLine());
        List<Edge> edges = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            int src = Integer.parseInt(parts[0]);
            int dest = Integer.parseInt(parts[1]);
            int weight = Integer.parseInt(parts[2]);
            edges.add(new Edge(src, dest, weight));
        }
        edges.sort(Comparator.comparingInt(o -> o.weight));
        kruskalMST.edges = edges;
        kruskalMST.numVertices = vertices;
        reader.close();

        return kruskalMST;
    }

    public BoruvkasMST readBoruvkasGraph(String filename) throws IOException {
        BoruvkasMST boruvkasMST = new BoruvkasMST();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        int vertices = Integer.parseInt(reader.readLine());
        List<Edge> edges = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            int src = Integer.parseInt(parts[0]);
            int dest = Integer.parseInt(parts[1]);
            int weight = Integer.parseInt(parts[2]);
            edges.add(new Edge(src, dest, weight));
        }
        edges.sort(Comparator.comparingInt(o -> o.weight));
        boruvkasMST.edges = edges;
        boruvkasMST.numVertices = vertices;
        reader.close();

        return boruvkasMST;
    }

}
