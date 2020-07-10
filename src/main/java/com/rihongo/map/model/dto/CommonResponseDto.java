package com.rihongo.map.model.dto;

import lombok.Builder;

@Builder
public class CommonResponseDto<T> {
    private String message;

    private T data;
}
