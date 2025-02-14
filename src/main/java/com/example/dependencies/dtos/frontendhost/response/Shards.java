package com.example.dependencies.dtos.frontendhost.response;

@lombok.Data
public class Shards {
    private Long total;
    private Long successful;
    private Long skipped;
    private Long failed;
}
