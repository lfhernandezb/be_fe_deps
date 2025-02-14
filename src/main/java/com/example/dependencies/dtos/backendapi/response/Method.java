package com.example.dependencies.dtos.backendapi.response;

@lombok.Data
public class Method {
    private Long docCountErrorUpperBound;
    private Long sumOtherDocCount;
    private MethodBucket[] buckets;
}
