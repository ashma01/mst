package com.software.testing.mst;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@NoArgsConstructor(force = true)
public class PrimsMST {
    public final List<List<Edge>> adjList;
    public final int[] minEdgeWeight;
    public final int[] parent;
    public final PriorityQueue<Vertex> minHeap;
    public final int numVertices;
    public final Set<Integer> inMST = new HashSet<>();

    public PrimsMST(Integer numVertices) {
        if (numVertices <= 0) {
            // Initialize with an empty structure when no vertices are present
            this.numVertices = 0;
            this.adjList = Collections.emptyList();
            this.minEdgeWeight = new int[0];
            this.parent = new int[0];
            this.minHeap = new PriorityQueue<>(1, Comparator.comparingInt(Vertex::getWeight));
            return;
        }

        this.numVertices = numVertices;
        this.adjList = new ArrayList<>(numVertices);
        for (int i = 0; i < numVertices; i++) {
            adjList.add(new ArrayList<>());
        }
        this.minEdgeWeight = new int[numVertices];
        Arrays.fill(minEdgeWeight, Integer.MAX_VALUE);
        this.parent = new int[numVertices];
        Arrays.fill(parent, -1);
        this.minHeap = new PriorityQueue<>(numVertices, Comparator.comparingInt(Vertex::getWeight));
    }

    public void addEdge(int src, int dest, int weight) {
        // Check to avoid IndexOutOfBoundsException for empty graphs
        if (numVertices == 0) {
            return;
        }

        adjList.get(src).add(new Edge(src, dest, weight));
        adjList.get(dest).add(new Edge(dest, src, weight)); // For undirected graph
    }

    public void getMST() {
        // Early return if graph is empty
        if (numVertices == 0) {
            return;
        }

        List<Edge> mst = new ArrayList<>();

        minEdgeWeight[0] = 0;
        minHeap.add(new Vertex(0, 0));

        while (mst.size() < numVertices - 1 && !minHeap.isEmpty()) {
            Vertex current = minHeap.poll();
            if (current == null) {
                break;
            }
            int u = current.id;
            if (!inMST.add(u)) {
                continue;
            }

            if (parent[u] != -1) {
                mst.add(new Edge(parent[u], u, minEdgeWeight[u]));
            }

            for (Edge edge : adjList.get(u)) {
                int v = edge.to;
                int weight = edge.weight;

                if (!inMST.contains(v) && minEdgeWeight[v] > weight) {
                    minEdgeWeight[v] = weight;
                    parent[v] = u;
                    minHeap.add(new Vertex(v, weight));
                }
            }
        }
    }

    public int printMST() {
        if (numVertices == 0) {
            return 0;
        }

        int totalWeight = 0;
        for (int i = 1; i < numVertices; i++) {
            totalWeight += minEdgeWeight[i];
        }
        return totalWeight;
    }
}