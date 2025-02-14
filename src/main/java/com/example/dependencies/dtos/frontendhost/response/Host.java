package com.example.dependencies.dtos.frontendhost.response;

@lombok.Data
public class Host {
    private Long docCountErrorUpperBound;
    private Long sumOtherDocCount;
    private HostBucket[] buckets;
}
