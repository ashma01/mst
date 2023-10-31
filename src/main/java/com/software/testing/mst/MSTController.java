package com.software.testing.mst;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
public class MSTController {

    final
    PrimsMST prims;

    final
    KruskalMST kruskals;

    public MSTController(PrimsMST primMST, KruskalMST kruskals) {
        this.prims = primMST;
        this.kruskals = kruskals;
    }


    @GetMapping("/prims")
    public String getPrims(@RequestParam(value = "name", defaultValue = "World") String name) {

        int graph[][] = new int[][]{{0, 2, 0, 6, 0},
                {2, 0, 3, 8, 5},
                {0, 3, 0, 0, 7},
                {6, 8, 0, 0, 9},
                {0, 5, 7, 9, 0}};


        // Print the solution
        prims.primMST(graph);
        return "successfully ran prims";
    }

    @GetMapping("/kruskals")
    public String getKruskal(@RequestParam(value = "name", defaultValue = "World") String name) {
        int V = 4;
        List<KruskalMST.Edge> graphEdges = new ArrayList<KruskalMST.Edge>(
                List.of(new KruskalMST.Edge(0, 1, 10), new KruskalMST.Edge(0, 2, 6),
                        new KruskalMST.Edge(0, 3, 5), new KruskalMST.Edge(1, 3, 15),
                        new KruskalMST.Edge(2, 3, 4)));

        // Sort the edges in non-decreasing order
        // (increasing with repetition allowed)
        graphEdges.sort(new Comparator<KruskalMST.Edge>() {
            @Override
            public int compare(KruskalMST.Edge o1, KruskalMST.Edge o2) {
                return o1.weight - o2.weight;
            }
        });
        kruskals.kruskalAlgo(V, graphEdges);
        return "successfully ran kruskal";
    }
}