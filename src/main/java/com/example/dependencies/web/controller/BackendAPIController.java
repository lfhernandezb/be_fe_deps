package com.example.dependencies.web.controller;

import com.example.dependencies.dtos.Terms;
import com.example.dependencies.dtos.backendapi.request.*;
import com.example.dependencies.dtos.backendapi.request.Method;
import com.example.dependencies.dtos.backendapi.request.Path;
import com.example.dependencies.dtos.backendapi.request.Service;
import com.example.dependencies.dtos.backendapi.response.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
@RequestMapping("/api")
public class BackendAPIController {
    // define a constant for the API key
    private static final String API_KEY = "aS1BYzlaUUJsSVBwZ3B0OE9nTnc6WnA5eGRtckpUVGFsNzBWaVF6UVZJZw==";

    // define a logger
    private Logger logger = LoggerFactory.getLogger(BackendAPIController.class);

    @RequestMapping("/beapi")
    public ResponseEntity<Response> getAPIDependencies() {
        Terms terms1 = Terms.builder()
                .field("url.path")
                .build();

        Path path = Path.builder()
                .terms(terms1)
                .build();

        MethodAggs methodAggs = MethodAggs.builder()
                .path(path)
                .build();

        Terms terms2 = Terms.builder()
                .field("http.request.method")
                .build();

        Method method = Method.builder()
                .terms(terms2)
                .aggs(methodAggs)
                .build();

        ServiceAggs serviceAggs = ServiceAggs.builder()
                .method(method)
                .build();

        Terms terms4 = Terms.builder()
                .field("service.name")
                .build();

        Service service = Service.builder()
                .terms(terms4)
                .aggs(serviceAggs)
                .build();

        RequestAggs requestAggs = RequestAggs.builder()
                .service(service)
                .build();

        QueryTerms queryTerms = QueryTerms.builder()
                .httpRequestMethod(new String[]{"GET", "POST", "PUT", "DELETE"})
                .build();

        Query query = Query.builder()
                .terms(queryTerms)
                .build();

        Request request = Request.builder()
                .query(query)
                .aggs(requestAggs)
                .size(0L)
                .build();

        RestTemplate restTemplate = new RestTemplate();
        // add headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, "ApiKey " + API_KEY);
        HttpEntity<Request> httpRequest = new HttpEntity<>(request, headers);

        // String url = "http://localhost:8080/api/echo";
        String url = "http://172.31.218.37:9200/*apm*/_search";
        // send request and parse result
        // ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        System.out.println(request.toString());

        ResponseEntity<Response> responseEntity = restTemplate.postForEntity(url, httpRequest, Response.class);

        Response response = responseEntity.getBody();

        System.out.println(response.toString());
        /*
        Arrays.stream(dependenciesResponse.getAggregations().getServices().getBuckets()).map(
                b -> b.getKey()).forEach(System.out::println);

        Arrays.stream(dependenciesResponse.getAggregations().getServices().getBuckets()).flatMap(
                b -> Arrays.stream(b.getDatabase().getBuckets())).forEach(
                c -> System.out.println(c.getKey()));

         */

        Arrays.stream(response.getAggregations().getService().getBuckets()).map(
                b -> {
                    System.out.println(b.getKey());

                    Arrays.stream(b.getMethod().getBuckets()).map(
                            c -> {
                                System.out.println(c.getKey());

                                Arrays.stream(c.getPath().getBuckets()).map(
                                        d -> {
                                            System.out.println(d.getKey());
                                            return d;
                                        }
                                ).toArray(); //.forEach(System.out::println);
                                return c;
                            }
                    ).toArray(); //.forEach(System.out::println);


                    return b;
                }
        ).toArray(); //.forEach(System.out::println);

        // System.out.println(dependenciesResponse.getAggregations().getServices().getBuckets().toString());

        return ResponseEntity.ok(response);

    }
}

