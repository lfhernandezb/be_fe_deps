package com.example.dependencies.dtos.dbdependencies.response;

@lombok.Data
public class Instance {
    private Long docCountErrorUpperBound;
    private Long sumOtherDocCount;
    private InstanceBucket[] buckets;
}
