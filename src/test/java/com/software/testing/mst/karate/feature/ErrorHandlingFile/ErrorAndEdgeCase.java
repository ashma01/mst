package com.software.testing.mst.karate.feature.ErrorHandlingFile;
import com.intuit.karate.junit5.Karate;

public class ErrorAndEdgeCase {
        @Karate.Test
        Karate testErrorsAndEdgeCases() {
            return Karate.run("ErrorAndEdgeCase").relativeTo(this.getClass());
        }
   }

