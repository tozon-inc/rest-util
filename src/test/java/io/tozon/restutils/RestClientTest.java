package io.tozon.restutils;


import org.junit.jupiter.api.Test;

import java.util.HashMap;

class RestClientTest {

    @Test
    void get() {
        // create a new instance of RestClient
        RestClient restClient = RestClient.builder()
                .baseUrl("http://example.com")
                .headers(new HashMap<>())
                .build();

        try {
            // make a GET request
            String response = restClient.get("/");

            // assert that the response is not null
            assert response != null;

        } catch (Exception e) {
            e.printStackTrace();

            // assert that the test failed
            assert false;
        }
    }

    @Test
    void post() {
        RestClient restClient = RestClient.builder()
                .baseUrl("http://example.com")
                .headers(new HashMap<>())
                .build();
        try {
            // make a POST request
            String response = restClient.post("/", "");

            // assert that the response is not null
            assert response != null;

        } catch (Exception e) {

            // print the stack trace
            e.printStackTrace();

            // assert that the test failed
            assert false;
        }


        assert true;
    }
}