package com.rihongo.map.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rihongo.map.config.interceptor.KakaoAPIInterceptor;
import com.rihongo.map.service.SearchAPIService;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Configuration
@RequiredArgsConstructor
public class APICallConfig {

    private final KakaoAPIInterceptor kakaoAPIInterceptor;

    private final APIPropertiesConfig apiPropertiesConfig;

    @Bean("kakaoAPIOkHttpClient")
    public OkHttpClient kakaoAPIOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(kakaoAPIInterceptor)
                .build();
    }

    @Bean("kakaoSearchAPIService")
    public SearchAPIService kakaoSearchAPIService(
            @Qualifier("jacksonObjectMapper") ObjectMapper jacksonObjectMapper,
            @Qualifier("kakaoAPIOkHttpClient") OkHttpClient kakaoAPIOkHttpClient
    ) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiPropertiesConfig.getUrl().get("kakaoMap"))
                .addConverterFactory(JacksonConverterFactory.create(jacksonObjectMapper))
                .client(kakaoAPIOkHttpClient)
                .build();
        return retrofit.create(SearchAPIService.class);
    }
}
