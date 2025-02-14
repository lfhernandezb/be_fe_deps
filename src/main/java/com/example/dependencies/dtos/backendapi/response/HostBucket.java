package com.example.dependencies.dtos.backendapi.response;

@lombok.Data
public class HostBucket {
    private String key;
    private Long docCount;
    private Port port;
}
