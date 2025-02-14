package com.example.dependencies.dtos.apidependencies.response;

@lombok.Data
public class Shards {
    private Long total;
    private Long successful;
    private Long skipped;
    private Long failed;
}
