package com.software.testing.mst;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class KruskalMST {

     int numVertices;
     List<Edge> edges;

    public KruskalMST() {
        this.edges = new ArrayList<>();
    }

    // Function to find the MST
    public List<Edge> kruskalAlgo() {
        int j = 0;
        int noOfEdges = 0;

        // Allocate memory for creating V subsets
        Subset subsets[] = new Subset[numVertices];

        // Allocate memory for results
        Edge results[] = new Edge[numVertices];

        // Create V subsets with single elements
        for (int i = 0; i < numVertices; i++) {
            subsets[i] = new Subset(i, 0);
        }

        // Number of edges to be taken is equal to V-1
        while (noOfEdges < numVertices - 1) {

            // Pick the smallest edge. And increment
            // the index for next iteration
            Edge nextEdge = edges.get(j);
            int x = findRoot(subsets, nextEdge.from);
            int y = findRoot(subsets, nextEdge.to);

            // If including this edge doesn't cause cycle,
            // include it in result and increment the index
            // of result for next edge
            if (x != y) {
                results[noOfEdges] = nextEdge;
                union(subsets, x, y);
                noOfEdges++;
            }

            j++;
        }
        List<Edge> mst = new ArrayList<>();
        for (int i = 0; i < noOfEdges; i++) {
            mst.add(results[i]);
        }
        return mst;
    }

    public int printMST(List<Edge> mst) {
        int minCost = 0;
        for (Edge edge : mst) {
            minCost += edge.weight;
        }
        return minCost;
    }

    // Function to unite two disjoint sets
    private static void union(Subset[] subsets, int x,
                              int y) {
        int rootX = findRoot(subsets, x);
        int rootY = findRoot(subsets, y);

        if (subsets[rootY].rank < subsets[rootX].rank) {
            subsets[rootY].parent = rootX;
        } else if (subsets[rootX].rank
                < subsets[rootY].rank) {
            subsets[rootX].parent = rootY;
        } else {
            subsets[rootY].parent = rootX;
            subsets[rootX].rank++;
        }
    }

    // Function to find parent of a set
    private static int findRoot(Subset[] subsets, int i) {
        if (subsets[i].parent == i)
            return subsets[i].parent;

        subsets[i].parent
                = findRoot(subsets, subsets[i].parent);
        return subsets[i].parent;
    }
}
