package com.software.testing.mst;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MstManager {

    PrimsMST primsMST;
    KruskalMST kruskalMST;
    BoruvkasMST boruvkasMST;
    GraphReader graphReader;

    public MstManager(PrimsMST primMST, KruskalMST kruskals, BoruvkasMST boruvkasMST, GraphReader graphReader) {
        this.primsMST = primMST;
        this.kruskalMST = kruskals;
        this.boruvkasMST = boruvkasMST;
        this.graphReader = graphReader;
    }

    public Response runAlgo(String filename, String name) {
        Response response = new Response();
        long startTimeP = 0;
        long endTimeP = 0;
        int totalWeight = 0;
        if (name.equalsIgnoreCase("prims")) {
            startTimeP = System.currentTimeMillis();
            totalWeight = callPrims(filename);
            endTimeP = System.currentTimeMillis();
        } else if (name.equalsIgnoreCase("kruskals")) {
            startTimeP = System.currentTimeMillis();
            totalWeight = callKruskals(filename);
            endTimeP = System.currentTimeMillis();
        } else if (name.equalsIgnoreCase("boruvkas")) {
            startTimeP = System.currentTimeMillis();
            totalWeight = callBoruvkas(filename);
            endTimeP = System.currentTimeMillis();
        } else if (name.equalsIgnoreCase("All")) {
            startTimeP = System.currentTimeMillis();
            totalWeight = callAll(filename);
            endTimeP = System.currentTimeMillis();

        }
        response.setAlgo(name);
        response.setTotalWeight(totalWeight);
        response.setTimeTake(endTimeP - startTimeP + "ms");

        return response;
    }

    private int callAll(String filename) {
        int finalWeight = 0;
        int totalBoruvkaWeight = callBoruvkas(filename);
        int totalKruskalWeight = callKruskals(filename);
        int totalPrimWeight = callPrims(filename);
        if((totalPrimWeight == totalBoruvkaWeight) && (totalKruskalWeight == totalBoruvkaWeight)){
            finalWeight = totalPrimWeight;
        }
        return finalWeight;
    }

    private int callBoruvkas(String filename) {
        int totalWeight;
        try {
            boruvkasMST = graphReader.readBoruvkasGraph(filename);
            totalWeight = boruvkasMST.boruvkaMST();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error occurred while reading file");
        }
        return totalWeight;
    }

    private int callKruskals(String filename) {
        int totalWeight;
        try {
            kruskalMST = graphReader.readKruskalGraph(filename);
            List<Edge> mst = kruskalMST.kruskalAlgo();
            totalWeight = kruskalMST.printMST(mst);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error occurred while reading file");
        }
        return totalWeight;

    }

    private int callPrims(String filename) {
        int totalWeight;
        try {
            primsMST = graphReader.readPrimGraph(filename);
            primsMST.getMST();
            totalWeight = primsMST.printMST();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error occurred while reading file");
        }
        return totalWeight;
    }
}
