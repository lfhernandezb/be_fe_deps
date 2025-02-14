package com.example.dependencies.dtos.dbdependencies.response;

@lombok.Data
public class InstanceBucket {
    private String key;
    private Long docCount;
    private Sentence sentence;
}
