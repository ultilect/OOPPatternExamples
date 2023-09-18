package org.example.creationalPatterns;

import java.util.HashMap;

/**
 * Способ создания сложного объекта отдельными частями
 */

/*
    Например, создание http запроса(чисто условно).
    Также можно реализовывать различных билдеров наследуясь от 1 общего абстрактного
 */
enum HttpMethod {
    GET,
    POST,
    DELETE,
    PUT
}

enum HTTPVersion {
    VERSION_10,
    VERSION_11,
    VERSION_20
}
public class BuilderExample {
    public static void test() {
        HttpRequestExampleBuilder builder = new HttpRequestExampleBuilder();
        HttpRequestExample request =  builder
                .addMethod(HttpMethod.PUT)
                .addBody("Test body")
                .addHttpHeader("host", "localhost")
                .addHttpHeader("Content-Type", "text/html")
                .addHttpHeader("Content-Type", "application/json")
                .addHttpVersion(HTTPVersion.VERSION_11)
                .build();

        System.out.println(request.toString());
    }
}
class HttpRequestExample {
    HashMap<String, String> headers;
    HttpMethod method;
    String body;
    HTTPVersion requestVersion;

    public HttpRequestExample() {
        this.headers = new HashMap<>();
    }

    public void setMethod(final HttpMethod status) {
        this.method = status;
    }

    public void setBody(final String body) {
        this.body = body;
    }

    public void setRequestVersion(final HTTPVersion requestVersion) {
        this.requestVersion = requestVersion;
    }

    public void addRequestHeader(final String key, final String value) throws Exception {
        if (this.headers.containsKey(key)) throw new Exception(String.format("Header: %s already exists. " +
                "Cant set value: %s", key, value));
        this.headers.put(key, value);
    }

    @Override
    public String toString() {
        return "HttpRequestExample{" +
                "headers=" + headers +
                ", method=" + method +
                ", body='" + body + '\'' +
                ", requestVersion=" + requestVersion +
                '}';
    }
}

class HttpRequestExampleBuilder {
    private final HttpRequestExample httpRequest;

    public HttpRequestExampleBuilder() {
        this.httpRequest = new HttpRequestExample();
    }

    public HttpRequestExampleBuilder addMethod(HttpMethod method) {
        this.httpRequest.setMethod(method);
        return this;
    }

    public HttpRequestExampleBuilder addBody(String body) {
        //Some check of http request body
        this.httpRequest.setBody(body);
        return this;
    }

    public HttpRequestExampleBuilder addHttpVersion(HTTPVersion version) {
        this.httpRequest.setRequestVersion(version);
        return this;
    }

    public HttpRequestExampleBuilder addHttpHeader(String key, String value) {
        try {
            this.httpRequest.addRequestHeader(key, value);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return this;
    }

    public HttpRequestExample build() {
        return this.httpRequest;
    }
}