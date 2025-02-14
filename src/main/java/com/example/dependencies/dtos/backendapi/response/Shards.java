package com.example.dependencies.dtos.backendapi.response;

@lombok.Data
public class Shards {
    private Long total;
    private Long successful;
    private Long skipped;
    private Long failed;
}
