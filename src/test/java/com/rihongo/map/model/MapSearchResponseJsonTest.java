package com.rihongo.map.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.rihongo.map.model.dto.map.MapDocument;
import com.rihongo.map.model.dto.map.MapMetaInfo;
import com.rihongo.map.model.dto.map.MapSearchResponseDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@JsonTest
public class MapSearchResponseJsonTest {

    @Test
    public void jsonParseTest() throws JsonProcessingException {
        MapSearchResponseDto mapSearchResponseDto = new MapSearchResponseDto();
        MapDocument mapDocument = new MapDocument();
        mapDocument.setPlaceUrl("http://place.map.kakao.com/1584875571");
        MapMetaInfo mapMetaInfo = new MapMetaInfo();
        mapMetaInfo.setPageableCount(34);
        mapSearchResponseDto.setDocuments(Arrays.asList(mapDocument));
        mapSearchResponseDto.setMeta(mapMetaInfo);

        String json = "{\"documents\":[{\"address_name\":\"경기 이천시 마장면 이평리 477-7\"," +
                "\"category_group_code\":\"\"," +
                "\"category_group_name\":\"\"," +
                "\"category_name\":\"스포츠,레저 \\u003e 스포츠용품 \\u003e 스키,보드용품\"," +
                "\"distance\":\"\",\"id\":\"1584875571\"," +
                "\"phone\":\"031-637-2829\"," +
                "\"place_name\":\"AUGMENT SKI Test Center\"," +
                "\"place_url\":\"http://place.map.kakao.com/1584875571\"," +
                "\"road_address_name\":\"경기 이천시 마장면 지산로 58\"," +
                "\"x\":\"127.366573090717\"," +
                "\"y\":\"37.2188264269501\"}," +
                "{\"address_name\":\"대구 중구 동인동4가 445-5\"," +
                "\"category_group_code\":\"FD6\"," +
                "\"category_group_name\":\"음식점\"," +
                "\"category_name\":\"음식점 \\u003e 양식 \\u003e 이탈리안\"," +
                "\"distance\":\"\",\"id\":\"2041475612\"," +
                "\"phone\":\"070-8899-0230\"," +
                "\"place_name\":\"테스트키친\"," +
                "\"place_url\":\"http://place.map.kakao.com/2041475612\"," +
                "\"road_address_name\":\"대구 중구 국채보상로 735\"," +
                "\"x\":\"128.61100240249814\",\"y\":\"35.868687200159954\"}]," +
                "\"meta\":{\"is_end\":false," +
                "\"pageable_count\":34," +
                "\"same_name\":{\"keyword\":\"test\",\"region\":[]," +
                "\"selected_region\":\"\"}," +
                "\"total_count\":34}}";

        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json()
                .featuresToDisable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
                .propertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE)
                .modules(new JavaTimeModule())
                .build();

        MapSearchResponseDto parseResponseDto = objectMapper.readValue(json, MapSearchResponseDto.class);
        System.out.println(parseResponseDto.toString());

        assertThat(parseResponseDto.getDocuments().get(0).getPlaceUrl(), equalTo(mapSearchResponseDto.getDocuments().get(0).getPlaceUrl()));
        assertThat(parseResponseDto.getMeta().getPageableCount(), equalTo(mapSearchResponseDto.getMeta().getPageableCount()));

    }

}
