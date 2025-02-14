package com.example.dependencies.dtos.frontendhost.request;

@lombok.Data
@lombok.Builder
public class Request {
    private Query query;
    private RequestAggs aggs;
    private Long size;
}
