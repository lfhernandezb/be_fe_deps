package com.example.dependencies.dtos.dbdependencies.response;

@lombok.Data
public class Shards {
    private Long total;
    private Long successful;
    private Long skipped;
    private Long failed;
}
