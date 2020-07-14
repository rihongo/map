package com.rihongo.map.model.dto.map;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class MapSearchRequestDto {

    @NotEmpty(message = "검색어를 입력해주세요")
    private String query;

    private int page = 1;

    private int size = 10;
}
