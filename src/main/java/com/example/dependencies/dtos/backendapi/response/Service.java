package com.example.dependencies.dtos.backendapi.response;

@lombok.Data
public class Service {
    private Long docCountErrorUpperBound;
    private Long sumOtherDocCount;
    private ServiceBucket[] buckets;
}
