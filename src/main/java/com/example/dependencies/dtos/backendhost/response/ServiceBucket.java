package com.example.dependencies.dtos.backendhost.response;

@lombok.Data
public class ServiceBucket {
    private String key;
    private Long docCount;
    private Host host;
}
