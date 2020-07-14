package com.rihongo.map.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.webservices.client.WebServiceClientTest;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@WebServiceClientTest
@TestPropertySource(locations = "classpath:/api.properties")
public class MapSearchRestTemplateTest {

    @Value("${kakao.api.key}")
    private String apiKey;

    @Value("${kakao.api.url.map}")
    private String searchUrl;

    @Test
    public void mapSearchApICallTestByRestTemplate() {
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = new MediaType("application", "json", StandardCharsets.UTF_8);
        headers.setContentType(mediaType);
        headers.add("Authorization", apiKey);

        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);
        ResponseEntity<String> res = template.exchange(searchUrl + "keyword.json?query=" + "test"
                , HttpMethod.GET
                , httpEntity
                , String.class);

        System.out.println(res);
        assertThat(res.getStatusCode(), equalTo(HttpStatus.OK));
    }

}
