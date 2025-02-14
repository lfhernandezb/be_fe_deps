package com.example.dependencies.dtos.backendapi.response;

@lombok.Data
public class MethodBucket {
    private Key key;
    private Long docCount;
    private Path path;
}
