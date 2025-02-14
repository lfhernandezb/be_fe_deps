package com.example.dependencies.dtos.apidependencies.response;

@lombok.Data
public class Endpoint {
    private Long docCountErrorUpperBound;
    private Long sumOtherDocCount;
    private EndpointBucket[] buckets;
}
