package com.rihongo.map.model.dto.map;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MapMetaInfo {

    @JsonProperty("total_count")
    private int totalCount;

    @JsonProperty("pageable_count")
    private int pageableCount;

    @JsonProperty("is_end")
    private boolean isEnd;
}
