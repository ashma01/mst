package com.software.testing.mst;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MSTController.class)
public class MSTControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MstManager mstManager;

    @BeforeEach
    public void setUp() throws IOException {
        when(mstManager.runAlgo("testfile.txt", "prims")).thenReturn(new Response("prims", 10, "100ms"));
        when(mstManager.runAlgo("testfile.txt", "kruskals")).thenReturn(new Response("kruskals", 10, "100ms"));
        when(mstManager.runAlgo("testfile.txt", "boruvkas")).thenReturn(new Response("boruvkas", 10, "100ms"));
        when(mstManager.runAlgo("testfile.txt", "All")).thenReturn(new Response("All", 10, "100ms"));
    }

    @Test
    public void testGetPrims() throws Exception {
        mockMvc.perform(get("/prims")
                        .param("filepath", "testfile.txt")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.algo").value("prims"))
                .andExpect(jsonPath("$.totalWeight").value(10))
                .andExpect(jsonPath("$.timeTaken").value("100ms"));
    }

    @Test
    public void testGetKruskal() throws Exception {
        mockMvc.perform(get("/kruskals")
                        .param("filepath", "testfile.txt")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.algo").value("kruskals"))
                .andExpect(jsonPath("$.totalWeight").value(10))
                .andExpect(jsonPath("$.timeTaken").value("100ms"));
    }

    @Test
    public void testGetBoruvkas() throws Exception {
        mockMvc.perform(get("/boruvkas")
                        .param("filepath", "testfile.txt")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.algo").value("boruvkas"))
                .andExpect(jsonPath("$.totalWeight").value(10))
                .andExpect(jsonPath("$.timeTaken").value("100ms"));
    }

    @Test
    public void testGetMinimumSpanningTree() throws Exception {
        mockMvc.perform(get("/getMst")
                        .param("filepath", "testfile.txt")
                        .param("algoName", "All")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.algo").value("All"))
                .andExpect(jsonPath("$.totalWeight").value(10))
                .andExpect(jsonPath("$.timeTaken").value("100ms"));
    }

    @Test
    public void testGetMinimumSpanningTreeWithInvalidParams() throws Exception {
        mockMvc.perform(get("/getMst")
                        .param("filepath", "")
                        .param("algoName", "")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof RuntimeException))
                .andExpect(result -> assertEquals("invalid input params", result.getResolvedException().getMessage()));
    }
}
