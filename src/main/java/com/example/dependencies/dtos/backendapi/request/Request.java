package com.example.dependencies.dtos.backendapi.request;

@lombok.Data
@lombok.Builder
public class Request {
    private Query query;
    private RequestAggs aggs;
    private Long size;
}
