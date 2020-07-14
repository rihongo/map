package com.rihongo.map.model.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CommonResponseDto<T> {
    private String message;

    private T data;
}
