package com.example.dependencies.dtos.backendapi.response;

@lombok.Data
public class ServiceBucket {
    private String key;
    private Long docCount;
    private Method method;
}
