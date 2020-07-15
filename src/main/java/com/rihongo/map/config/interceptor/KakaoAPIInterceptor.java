package com.rihongo.map.config.interceptor;

import com.rihongo.map.config.APIPropertiesConfig;
import lombok.RequiredArgsConstructor;
import okhttp3.Interceptor;
import okhttp3.Response;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class KakaoAPIInterceptor implements Interceptor {

    private final APIPropertiesConfig apiPropertiesConfig;

    @Override
    public Response intercept(Chain chain) throws IOException {
        MediaType mediaType = new MediaType("application", "json", StandardCharsets.UTF_8);

        return chain.proceed(
                chain.request().newBuilder()
                        .addHeader("Content-Type", String.valueOf(mediaType))
                        .addHeader("Authorization", apiPropertiesConfig.getKey().get("kakao"))
                        .build()
        );
    }
}
