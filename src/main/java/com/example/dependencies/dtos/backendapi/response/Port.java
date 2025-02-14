package com.example.dependencies.dtos.backendapi.response;

@lombok.Data
public class Port {
    private Long docCountErrorUpperBound;
    private Long sumOtherDocCount;
    private PortBucket[] buckets;
}
