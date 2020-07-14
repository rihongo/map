package com.rihongo.map.model.dto.map;

import com.rihongo.map.exception.APICommunicationException;
import lombok.Data;

import java.util.List;

@Data
public class MapSearchResponseDto {

    private List<MapDocument> documents;

    private MapMetaInfo meta;

    public List<MapDocument> getDocuments() {
        if (documents == null) {
            throw new APICommunicationException("api.communication.empty.message");
        }
        return documents;
    }
}
