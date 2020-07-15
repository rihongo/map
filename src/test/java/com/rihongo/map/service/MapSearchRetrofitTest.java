package com.rihongo.map.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.rihongo.map.model.dto.map.MapSearchResponseDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.webservices.client.WebServiceClientTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@WebServiceClientTest
@TestPropertySource(locations = "classpath:/api.properties")
public class MapSearchRetrofitTest {

    @Value("${api.key.kakao}")
    private String apiKey;

    @Value("${api.url.kakaoMap}")
    private String searchUrl;

    @Test
    public void mapSearchApICallTestByRetrofit() throws IOException {
        String keyword = "카카오 ";

        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json()
                .featuresToDisable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
                .propertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE)
                .modules(new JavaTimeModule())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(searchUrl)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build();

        SearchAPIService searchApiService = retrofit.create(SearchAPIService.class);
        Response<MapSearchResponseDto> mapSearchResponseDtoResponse = searchApiService.getMapByKeyword(apiKey, keyword, 1, 10).execute();
        System.out.println(mapSearchResponseDtoResponse);
        assertThat(mapSearchResponseDtoResponse.code(), equalTo(HttpStatus.OK.value()));
        assertThat(mapSearchResponseDtoResponse.body().getDocuments(), is(not(empty())));
        assertThat(mapSearchResponseDtoResponse.body().getMeta(), is(not(nullValue())));
    }

    @Test
    public void mapSearchApICallWrongAPIKeyTestWithException() throws IOException {
        String keyword = "카카오 ";
        String wrongAPIKey = apiKey + "1234";

        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json()
                .featuresToDisable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
                .propertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE)
                .modules(new JavaTimeModule())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(searchUrl)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build();

        SearchAPIService searchApiService = retrofit.create(SearchAPIService.class);
        Response<MapSearchResponseDto> mapSearchResponseDtoResponse = searchApiService.getMapByKeyword(wrongAPIKey, keyword, 1, 10).execute();
        System.out.println(mapSearchResponseDtoResponse);
        assertThat(mapSearchResponseDtoResponse.code(), equalTo(HttpStatus.UNAUTHORIZED.value()));
    }
}
