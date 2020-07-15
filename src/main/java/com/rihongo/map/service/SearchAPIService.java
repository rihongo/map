package com.rihongo.map.service;

import com.rihongo.map.model.dto.map.MapSearchResponseDto;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface SearchAPIService {

    @GET("keyword.json")
    Call<MapSearchResponseDto> getMapByKeyword(@Header("Authorization") String key
            , @Query("query") String keyword
            , @Query("page") int page
            , @Query("size") int size);

    @GET("keyword.json")
    Call<MapSearchResponseDto> getMapByKeyword(@Query("query") String keyword
            , @Query("page") int page
            , @Query("size") int size);

}
