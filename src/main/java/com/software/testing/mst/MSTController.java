package com.software.testing.mst;

import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
public class MSTController {

    MstManager mstManager;

    public MSTController(MstManager mstManager) {
        this.mstManager = mstManager;
    }


    @GetMapping("/prims")
    public Response getPrims(@RequestParam(value = "filepath") String filename) {
        return mstManager.runAlgo(filename, "prims");
    }

    @GetMapping("/kruskals")
    public Response getKruskal(@RequestParam(value = "filepath") String filename) {

        return mstManager.runAlgo(filename, "kruskals");
    }

    @GetMapping("/boruvkas")
    public Response getBoruvkas(@RequestParam(value = "filepath") String filename) {

        return mstManager.runAlgo(filename, "boruvkas");
    }

    @GetMapping("/getMst")
    public Response getMinimumSpanningTree(@RequestParam(value = "filepath") String filename, @RequestParam(value = "algoName", required = false) String name) {

        if (filename.isEmpty() || name.isEmpty()) {
            throw new InvalidInputException("invalid input params");        }
        return mstManager.runAlgo(filename, name);
    }

}