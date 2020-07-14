package com.rihongo.map.model.dto.map;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MapDocument {
    String id;

    @JsonProperty("place_name")
    String placeName;

    @JsonProperty("category_name")
    String categoryName;

    @JsonProperty("category_group_code")
    String categoryGroupCode;

    @JsonProperty("category_group_name")
    String categoryGroupName;

    String phone;

    @JsonProperty("address_name")
    String addressName;

    @JsonProperty("road_address_name")
    String roadAddressName;

    @JsonProperty("place_url")
    String placeUrl;

    String shortcuts;
}
