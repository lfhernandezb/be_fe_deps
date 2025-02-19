package com.example.dependencies.web.controller;

import com.example.dependencies.dtos.*;

import com.example.dependencies.dtos.apidependencies.request.*;
import com.example.dependencies.dtos.apidependencies.response.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

@RestController
@RequestMapping("/api")
public class APIDepController {
    // define a constant for the API key
    private static final String API_KEY = "aS1BYzlaUUJsSVBwZ3B0OE9nTnc6WnA5eGRtckpUVGFsNzBWaVF6UVZJZw==";

    // define a logger
    private Logger logger = LoggerFactory.getLogger(APIDepController.class);

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

    @RequestMapping("/apidep")
    public ResponseEntity<Response> getAPIDependencies() {
        Terms terms1 = Terms.builder()
                .field("span.name")
                .build();

        Endpoint endpoint = Endpoint.builder()
                .terms(terms1)
                .build();

        HostAggs hostAggs = HostAggs.builder()
                .endpoint(endpoint)
                .build();

        Terms terms2 = Terms.builder()
                .field("destination.address")
                .build();

        Host host = Host.builder()
                .terms(terms2)
                .aggs(hostAggs)
                .build();

        ServiceAggs serviceAggs = ServiceAggs.builder()
                .host(host)
                .build();

        Terms terms4 = Terms.builder()
                .field("service.name")
                .build();

        Service service = Service.builder()
                .terms(terms4)
                .aggs(serviceAggs)
                .build();

        RequestAggs aggs = RequestAggs.builder()
                .service(service)
                .build();

        Term term1 = Term.builder()
                .spanType("external")
                .build();

        Term term2 = Term.builder()
                .spanSubtype("http")
                .build();

        Should should1 = Should.builder()
                .term(term1)
                .build();

        Should should2 = Should.builder()
                .term(term2)
                .build();

        Bool bool = Bool.builder()
                .shoulds(new Should[]{should1, should2})
                .build();

        Query query = Query.builder()
                .bool(bool)
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

                    Arrays.stream(b.getHost().getBuckets()).map(
                            c -> {
                                System.out.println(c.getKey());
                                Arrays.stream(c.getEndpoint().getBuckets()).map(
                                        d -> {
                                            // d.geyKey() tiene la forma MÉTODO URL
                                            // obtengo el metodo y la url
                                            System.out.println(d.getKey());
                                            String[] methodUrl = d.getKey().split(" ");
                                            System.out.println(methodUrl[0]);

                                            try {
                                                URL url2 = new URL(methodUrl[1]);
                                                System.out.println(url2.getHost());
                                                System.out.println(url2.getPath());
                                                System.out.println(url2.getProtocol());

                                            } catch (MalformedURLException e) {
                                                throw new RuntimeException(e);
                                            }

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
