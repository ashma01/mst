package com.software.testing.mst.karate.feature;

import com.intuit.karate.junit5.Karate;

public class example {
   @Karate.Test
    Karate testPrims() {
        return Karate.run("src/test/java/com/software/testing/mst/karate/feature/example.feature");
    }
}