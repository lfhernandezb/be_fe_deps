package com.example.dependencies.web.controller;

import com.example.dependencies.dtos.*;

import com.example.dependencies.dtos.dbdependencies.request.*;
import com.example.dependencies.dtos.dbdependencies.request.Host;
import com.example.dependencies.dtos.dbdependencies.request.HostAggs;
import com.example.dependencies.dtos.dbdependencies.request.Query;
import com.example.dependencies.dtos.dbdependencies.request.Request;
import com.example.dependencies.dtos.dbdependencies.request.RequestAggs;
import com.example.dependencies.dtos.dbdependencies.request.Service;
import com.example.dependencies.dtos.dbdependencies.request.ServiceAggs;
import com.example.dependencies.dtos.dbdependencies.response.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
@RequestMapping("/api")
public class DBDepController {
    // define a constant for the API key
    private static final String API_KEY = "aS1BYzlaUUJsSVBwZ3B0OE9nTnc6WnA5eGRtckpUVGFsNzBWaVF6UVZJZw==";

    // define a logger
    private Logger logger = LoggerFactory.getLogger(DBDepController.class);

    /*
    // echo endpoint
    @PostMapping("/echo")
    public ResponseEntity<String> echo(@RequestBody String body) {
        logger.info("Received request: " + body);
        return ResponseEntity.ok(body);
    }

    @GetMapping("/hello")
    public ResponseEntity<String> get() {
        return ResponseEntity.ok("Hello World");
    }

     */

    @RequestMapping("/dbdep")
    public ResponseEntity<Response> getDBDependencies() {
        Terms terms1 = Terms.builder()
                .field("span.name")
                .build();

        Sentence sentence = Sentence.builder()
                .terms(terms1)
                .build();

        InstanceAggs instanceAggs = InstanceAggs.builder()
                .sentence(sentence)
                .build();

        Terms terms2 = Terms.builder()
                .field("span.db.instance")
                .build();

        Instance instance = Instance.builder()
                .terms(terms2)
                .aggs(instanceAggs)
                .build();

        HostAggs hostAggs = HostAggs.builder()
                .instance(instance)
                .build();

        Terms terms3 = Terms.builder()
                .field("destination.address")
                .build();

        Host host = Host.builder()
                .terms(terms3)
                .aggs(hostAggs)
                .build();

        Terms terms4 = Terms.builder()
                .field("span.subtype")
                .build();

        EngineTypeAggs engineTypeAggs = EngineTypeAggs.builder()
                .host(host)
                .build();

        EngineType engineType = EngineType.builder()
                .terms(terms4)
                .aggs(engineTypeAggs)
                .build();

        ServiceAggs serviceAggs = ServiceAggs.builder()
                .engineType(engineType)
                .build();

        Terms terms5 = Terms.builder()
                .field("service.name")
                .build();

        Service service = Service.builder()
                .terms(terms5)
                .aggs(serviceAggs)
                .build();

        RequestAggs aggs = RequestAggs.builder()
                .service(service)
                .build();

        Term term = Term.builder()
                .spanType("db")
                .build();

        Query query = Query.builder()
                .term(term)
                .build();

        Request request = Request.builder()
                .query(query)
                .aggs(aggs)
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

                    Arrays.stream(b.getEngineType().getBuckets()).map(
                            c -> {
                                System.out.println(c.getKey());
                                Arrays.stream(c.getHost().getBuckets()).map(
                                        d -> {
                                            System.out.println(d.getKey());
                                            Arrays.stream(d.getInstance().getBuckets()).map(
                                                    e -> {
                                                        System.out.println(e.getKey());
                                                        return e;
                                                    }
                                            ).toArray(); //.forEach(System.out::println);
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