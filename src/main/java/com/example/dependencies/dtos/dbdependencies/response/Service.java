package com.example.dependencies.dtos.dbdependencies.response;

@lombok.Data
public class Service {
    private Long docCountErrorUpperBound;
    private Long sumOtherDocCount;
    private ServiceBucket[] buckets;
}
