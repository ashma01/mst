package com.software.testing.mst;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BoruvkasMST {

    int numVertices; // No. of vertices

    List<Edge> edges;

    private int find(List<Integer> parent, int i) {
        if (parent.get(i) == i) {
            return i;
        }
        return find(parent, parent.get(i));
    }

    private void unionSet(List<Integer> parent,
                          List<Integer> rank, int x, int y) {
        int xroot = find(parent, x);
        int yroot = find(parent, y);

        if (rank.get(xroot) < rank.get(yroot)) {
            parent.set(xroot, yroot);
        } else if (rank.get(xroot) > rank.get(yroot)) {
            parent.set(yroot, xroot);
        }

        else {
            parent.set(yroot, xroot);
            rank.set(xroot, rank.get(xroot) + 1);
        }
    }

    public Integer boruvkaMST() {
        List<Integer> parent = new ArrayList<>();

        List<Integer> rank = new ArrayList<>();

        List<List<Integer>> cheapest = new ArrayList<>();

        int numTrees = numVertices;
        int MSTweight = 0;

        for (int node = 0; node < numVertices; node++) {
            parent.add(node);
            rank.add(0);
            cheapest.add(Arrays.asList(-1, -1, -1));
        }

        while (numTrees > 1) {

            for (Edge edge : edges) {

                int u = edge.from, v = edge.to,
                        w = edge.weight;
                int set1 = find(parent, u),
                        set2 = find(parent, v);

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

            for (int node = 0; node < numVertices; node++) {

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
                list.set(2, -1);
            }
        }
        return MSTweight;
    }
}