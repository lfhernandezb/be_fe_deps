package com.example.dependencies.dtos.dbdependencies.response;

@lombok.Data
public class HostBucket {
    private String key;
    private Long docCount;
    private Instance instance;
}
