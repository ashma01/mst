package com.software.testing.mst;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ResponseTest {

    @Test
    public void testResponse() {
        Response response = new Response();
        response.setAlgo("prims");
        response.setTotalWeight(10);
        response.setTimeTaken("100ms");

        assertEquals("prims", response.getAlgo());
        assertEquals(10, response.getTotalWeight());
        assertEquals("100ms", response.getTimeTaken());
    }

    @Test
    public void testResponseConstructor() {
        Response response = new Response("prims", 10, "100ms");

        assertEquals("prims", response.getAlgo());
        assertEquals(10, response.getTotalWeight());
        assertEquals("100ms", response.getTimeTaken());
    }
}
