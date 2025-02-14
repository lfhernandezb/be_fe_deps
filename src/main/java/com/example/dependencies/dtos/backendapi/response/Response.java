package com.example.dependencies.dtos.backendapi.response;

@lombok.Data
public class Response {
    private Long took;
    private Boolean timedOut;
    private Shards shards;
    private Hits hits;
    private Aggregations aggregations;
}
