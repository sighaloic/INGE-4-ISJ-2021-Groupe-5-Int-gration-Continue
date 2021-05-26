package com.example.IntegrationContinue;

import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class HttpClientGetStatusCodeTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }


    @Test
     void httpClientGetStatus() throws ClientProtocolException, IOException {

        System.out.println("******** SERVER CODE STATUS 200 TEST *****************");
        String GOOGLE_URL = "https://helloworld-loic.herokuapp.com/";

        CloseableHttpClient instance;

        CloseableHttpResponse response;
        HttpGet httpGet = new HttpGet(GOOGLE_URL);
        instance = HttpClientBuilder.create().build();

        response = instance.execute(httpGet);

        System.out.println("response.getStatusLine() :: " + response.getStatusLine());
        final int statusCode = response.getStatusLine().getStatusCode();

        int code = HttpStatus.SC_OK;

        if (code == statusCode) {

            System.out.println("TEST PASSED, Status Code  : " + code);
        } else {
            System.out.println("TEST FAILLED, Status Code  : " + code + " (expected 200) ");
        }
    }
}