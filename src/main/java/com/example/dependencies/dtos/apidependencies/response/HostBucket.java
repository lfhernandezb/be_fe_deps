package com.example.dependencies.dtos.apidependencies.response;

@lombok.Data
public class HostBucket {
    private String key;
    private Long docCount;
    private Endpoint endpoint;
}
