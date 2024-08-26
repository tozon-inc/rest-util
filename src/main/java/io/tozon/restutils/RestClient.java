package io.tozon.restutils;

import com.google.gson.Gson;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * Utility class for making REST calls using OkHttp
 */
public class RestClient {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    //Logging Interceptor
    private static final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    //builder
    private static final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
    /*
     * Utility methods to make REST calls using OkHttp
     */
    private final String baseUrl;
    private Map<String, String> headers;

    RestClient(String baseUrl, Map<String, String> headers) {
        this.baseUrl = baseUrl;
        this.headers = headers;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }



    /**
     * Performs a GET request
     *
     * @param endpoint the url you are querying e.g <a href="http://localhost:8080/api/v2/coverage-check">...</a>
     * @return the response body as a string
     * @throws IOException
     */
    public String get(String endpoint) throws IOException {
        Request request = new Request.Builder().url(baseUrl + endpoint).headers(Headers.of(headers)).build();
        try (Response response = client.newCall(request).execute()) {
            return getResponseBody(response);
        }
    }

    //parse object to json using GsonParse. The object is a Map<String, Object>

    /**
     * Performs a POST request
     *
     * @param endpoint - the url to post to
     * @param body     - the body of the request
     * @return - the response body as a string
     * @throws IOException - if there was a problem with the request or response
     */
    public String post(String endpoint, Object body) throws IOException {
        RequestBody requestBody = RequestBody.create(new Gson().toJson(body), JSON);
        Request request = new Request.Builder().url(baseUrl + endpoint).headers(Headers.of(headers)).post(requestBody).build();
        try (Response response = client.newCall(request).execute()) {
            return getResponseBody(response);
        }
    }


    /**
     * Performs a PUT request
     *
     * @param endpoint - the url to put to
     * @param body     - the body of the request
     * @return - the response body as a string
     * @throws IOException - if there was a problem with the request or response
     */
    public String put(String endpoint, String body) throws IOException {
        RequestBody requestBody = RequestBody.create(body, JSON);
        Request request = new Request.Builder().url(baseUrl + endpoint).headers(Headers.of(headers)).put(requestBody).build();
        try (Response response = client.newCall(request).execute()) {
            return getResponseBody(response);
        }
    }

    /**
     * Performs a DELETE request to the specified endpoint
     *
     * @param endpoint - the url to delete from
     * @return - the response body as a string
     * @throws IOException - if there was a problem with the request or response
     */
    public String delete(String endpoint) throws IOException {
        Request request = new Request.Builder().url(baseUrl + endpoint).headers(Headers.of(headers)).delete().build();
        try (Response response = client.newCall(request).execute()) {
            return getResponseBody(response);
        }
    }

    // Utility method to get response body

    /**
     * Utility method to get response body
     *
     * @param response - the response object
     * @return - the response body as a string
     * @throws IOException - if there was a problem with the request or response
     */
    private String getResponseBody(Response response) throws IOException {
        if (response.isSuccessful() && Objects.nonNull(response.body())) {

            return response.body().string();

        } else {
            if (Objects.nonNull(response.body()))
                throw new IOException("Unexpected code " + response + " with body " + response.body().string());
            else throw new IOException("Unexpected code " + response);
        }
    }

    public static class RestClientBuilder {
        private String baseUrl;
        private Map<String, String> headers;

        RestClientBuilder() {
        }

        public RestClientBuilder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public RestClientBuilder headers(Map<String, String> headers) {
            this.headers = headers;
            return this;
        }

        public RestClient build() {
            return new RestClient(this.baseUrl, this.headers);
        }

        public String toString() {
            return "RestClient.RestClientBuilder(baseUrl=" + this.baseUrl + ", headers=" + this.headers + ")";
        }
    }
}
