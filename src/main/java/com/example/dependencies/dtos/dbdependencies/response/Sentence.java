package com.example.dependencies.dtos.dbdependencies.response;

@lombok.Data
public class Sentence {
    private Long docCountErrorUpperBound;
    private Long sumOtherDocCount;
    private SentenceBucket[] buckets;
}
