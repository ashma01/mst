package com.software.testing.mst.karate.feature;

import com.intuit.karate.junit5.Karate;

public class example {
   @Karate.Test
    Karate testPrims() {
        return Karate.run("example").relativeTo(this.getClass());
    }
}