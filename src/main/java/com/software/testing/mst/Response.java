package com.software.testing.mst;


import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class Response {
    String algo;
    String timeTake;
    Integer totalWeight;
}
