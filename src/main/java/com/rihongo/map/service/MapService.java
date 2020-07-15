package com.rihongo.map.service;

import com.rihongo.map.config.APIPropertiesConfig;
import com.rihongo.map.exception.APICommunicationException;
import com.rihongo.map.model.dto.map.MapSearchRequestDto;
import com.rihongo.map.model.dto.map.MapSearchResponseDto;
import com.rihongo.map.model.entities.MapSearchHistory;
import com.rihongo.map.model.repository.MapSearchHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;

@Service
@Slf4j
public class MapService {

    private final APIPropertiesConfig apiPropertiesConfig;

    private final SearchAPIService searchAPIService;

    private final MapSearchHistoryRepository mapSearchHistoryRepository;

    public MapService(APIPropertiesConfig apiPropertiesConfig,
                      MapSearchHistoryRepository mapSearchHistoryRepository,
                      @Qualifier("kakaoSearchAPIService") SearchAPIService searchAPIService) {
        this.apiPropertiesConfig = apiPropertiesConfig;
        this.mapSearchHistoryRepository = mapSearchHistoryRepository;
        this.searchAPIService = searchAPIService;
    }

    public MapSearchResponseDto search(MapSearchRequestDto requestDto) {
        MapSearchResponseDto mapSearchResponseDto;
        try {
            Response<MapSearchResponseDto> response = searchAPIService.getMapByKeyword(
                    requestDto.getQuery(),
                    requestDto.getPage(),
                    requestDto.getSize()).execute();

            mapSearchResponseDto = response.body();
            mapSearchResponseDto.getDocuments().forEach(
                    mapDocument -> mapDocument.setShortcuts(apiPropertiesConfig.getUrl().get("kakaoShortcuts") + mapDocument.getId())
            );
        } catch (IOException e) {
            throw new APICommunicationException("api.communication.fail.message");
        }

        return mapSearchResponseDto;
    }

    @Transactional(isolation = READ_COMMITTED)
    public void saveKeyword(String keyword) {
        mapSearchHistoryRepository.findById(keyword).ifPresentOrElse(
                searchHistory -> mapSearchHistoryRepository.save(searchHistory.setCount()),
                () -> mapSearchHistoryRepository.save(MapSearchHistory.builder()
                        .keyword(keyword)
                        .build())
        );
    }

    public List<MapSearchHistory> findTopKeyword() {
        Pageable pageable = PageRequest.of(0, 10);
        return mapSearchHistoryRepository.findByOrderByCountDesc(pageable);
    }
    
}
