package com.example.dependencies.dtos.backendapi.response;

@lombok.Data
public class Path {
    private Long docCountErrorUpperBound;
    private Long sumOtherDocCount;
    private PathBucket[] buckets;
}
