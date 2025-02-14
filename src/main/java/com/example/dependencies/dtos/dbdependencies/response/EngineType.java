package com.example.dependencies.dtos.dbdependencies.response;

@lombok.Data
public class EngineType {
    private Long docCountErrorUpperBound;
    private Long sumOtherDocCount;
    private EngineTypeBucket[] buckets;
}
