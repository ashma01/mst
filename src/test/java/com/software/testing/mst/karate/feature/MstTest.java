package com.software.testing.mst.karate.feature;

import com.intuit.karate.junit5.Karate;

public class MstTest {
   @Karate.Test
    Karate testPrims() {
        return Karate.run("MstTest").relativeTo(this.getClass());
    }

}