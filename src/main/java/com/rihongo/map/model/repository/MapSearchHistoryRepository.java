package com.rihongo.map.model.repository;

import com.rihongo.map.model.entities.MapSearchHistory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MapSearchHistoryRepository extends JpaRepository<MapSearchHistory, String> {

    List<MapSearchHistory> findByOrderByCountDesc(Pageable pageable);
}
