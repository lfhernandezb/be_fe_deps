package com.example.dependencies.dtos.frontendhost.response;

@lombok.Data
public class Response {
    private Long took;
    private Boolean timedOut;
    private Shards shards;
    private Hits hits;
    private Aggregations aggregations;
}
