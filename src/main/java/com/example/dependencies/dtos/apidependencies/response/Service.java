package com.example.dependencies.dtos.apidependencies.response;

@lombok.Data
public class Service {
    private Long docCountErrorUpperBound;
    private Long sumOtherDocCount;
    private ServiceBucket[] buckets;
}
