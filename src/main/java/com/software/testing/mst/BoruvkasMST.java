package com.software.testing.mst;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BoruvkasMST {
    // Boruvka's algorithm to find Minimum Spanning
// Tree of a given connected, undirected and weighted graph

    // Class to represent a graph

    int numVertices; // No. of vertices

    List<Edge> edges;
//    List<List<Integer>> graph; // default dictionary to store graph

//    public BoruvkasMST() {
//        graph = new ArrayList<>();
//    }

    // function to add an edge to graph
//    public void addEdge(int u, int v, int w) {
//        graph.add(Arrays.asList(u, v, w));
//    }

    // A utility function to find set of an element i
// (uses path compression technique)
    private int find(List<Integer> parent, int i) {
        if (parent.get(i) == i) {
            return i;
        }
        return find(parent, parent.get(i));
    }

    // A function that does union of two sets of x and y
// (uses union by rank)
    private void unionSet(List<Integer> parent,
                          List<Integer> rank, int x, int y) {
        int xroot = find(parent, x);
        int yroot = find(parent, y);

        // Attach smaller rank tree under root of high rank
        // tree (Union by Rank)
        if (rank.get(xroot) < rank.get(yroot)) {
            parent.set(xroot, yroot);
        } else if (rank.get(xroot) > rank.get(yroot)) {
            parent.set(yroot, xroot);
        }

        // If ranks are same, then make one as root and
        // increment its rank by one
        else {
            parent.set(yroot, xroot);
            rank.set(xroot, rank.get(xroot) + 1);
        }
    }

    // The main function to construct MST using Kruskal's
// algorithm
    public Integer boruvkaMST() {
        List<Integer> parent = new ArrayList<>();

        // An array to store index of the cheapest edge subset. It stores [u,v,w] for each component
        List<Integer> rank = new ArrayList<>();

        List<List<Integer>> cheapest = new ArrayList<>();

        // Initially there are V different trees. There will be one tree that will be MST
        int numTrees = numVertices;
        int MSTweight = 0;

        // Create V subsets with single elements
        for (int node = 0; node < numVertices; node++) {
            parent.add(node);
            rank.add(0);
            cheapest.add(Arrays.asList(-1, -1, -1));
        }

        // Keep combining components (or sets) until all
        // components are not combined into single MST
        while (numTrees > 1) {

            // Traverse through all edges and update
            // cheapest of every component
            for (Edge edge : edges) {

                // Find components (or sets) of two corners
                // of current edge
                int u = edge.from, v = edge.to,
                        w = edge.weight;
                int set1 = find(parent, u),
                        set2 = find(parent, v);

                // If two corners of current edge belong to
                // same set, ignore current edge. Else check
                // if current edge is closer to previous
                // cheapest edges of set1 and set2
                if (set1 != set2) {
                    if (cheapest.get(set1).get(2) == -1
                            || cheapest.get(set1).get(2) > w) {
                        cheapest.set(
                                set1, Arrays.asList(u, v, w));
                    }
                    if (cheapest.get(set2).get(2) == -1
                            || cheapest.get(set2).get(2) > w) {
                        cheapest.set(
                                set2, Arrays.asList(u, v, w));
                    }
                }
            }

            // Consider the above picked cheap edges and add them to MST
            for (int node = 0; node < numVertices; node++) {

                // Check if cheapest for current set exists
                if (cheapest.get(node).get(2) != -1) {
                    int u = cheapest.get(node).get(0),
                            v = cheapest.get(node).get(1),
                            w = cheapest.get(node).get(2);
                    int set1 = find(parent, u),
                            set2 = find(parent, v);
                    if (set1 != set2) {
                        MSTweight += w;
                        unionSet(parent, rank, set1, set2);
                        numTrees--;
                    }
                }
            }
            for (List<Integer> list : cheapest) {
                // reset cheap array
                list.set(2, -1);
            }
        }
        return MSTweight;
    }
}



