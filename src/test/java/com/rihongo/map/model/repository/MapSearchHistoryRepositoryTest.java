package com.rihongo.map.model.repository;

import com.rihongo.map.model.entities.MapSearchHistory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MapSearchHistoryRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private MapSearchHistoryRepository mapSearchHistoryRepository;

    @Test
    public void userSearchHistoryRegisterTest() {
        String keyword = "카카오";
        MapSearchHistory mapSearchHistory = MapSearchHistory.builder()
                .keyword(keyword)
                .build();

        testEntityManager.persist(mapSearchHistory);

        assertThat(mapSearchHistoryRepository.findById(keyword).orElse(new MapSearchHistory()), is(mapSearchHistory));
    }

    @Test
    public void getTop10SearchHistoryTest() {
        String keywordTop1 = "카카오뱅크";
        String keywordTop2 = "카뱅";
        List<String> keywords = Arrays.asList(keywordTop1, keywordTop2);

        for (String keyword : keywords) {
            testEntityManager.persist(MapSearchHistory.builder()
                    .keyword(keyword)
                    .count(keyword.length())
                    .build());
        }

        Pageable pageable = PageRequest.of(0, 10);

        assertThat(mapSearchHistoryRepository.findByOrderByCountDesc(pageable).get(0).getKeyword(), equalTo(keywordTop1));
    }
}
