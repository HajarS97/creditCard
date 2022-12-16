package com.europcar.create_redit_card.client.config;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static java.nio.charset.Charset.defaultCharset;
import static org.springframework.util.StreamUtils.copyToString;

public class MockConfig {

    public static void mockWithSuccess() throws IOException {
        stubFor(post("/notification/sendEmail")
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(copyToString(MockConfig.class.getClassLoader()
                                        .getResourceAsStream("response/successcase.json"),
                                defaultCharset()))));
    }

     public static void mockWithError() throws IOException {
        stubFor(post("/notification/sendEmail")
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.BAD_REQUEST.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(copyToString(MockConfig.class.getClassLoader()
                                        .getResourceAsStream("response/errorcase.json"),
                                defaultCharset()))));
    }



}
