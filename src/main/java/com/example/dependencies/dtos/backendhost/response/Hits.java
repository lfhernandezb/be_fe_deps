package com.example.dependencies.dtos.backendhost.response;

@lombok.Data
public class Hits {
    private Total total;
    private Object maxScore;
    private Object[] hits;
}
