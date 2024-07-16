package com.software.testing.mst;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    String algo;
    Integer totalWeight;
    String timeTaken;
}
