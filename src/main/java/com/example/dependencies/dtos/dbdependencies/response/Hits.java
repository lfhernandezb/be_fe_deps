package com.example.dependencies.dtos.dbdependencies.response;

@lombok.Data
public class Hits {
    private Total total;
    private Object maxScore;
    private Object[] hits;
}
