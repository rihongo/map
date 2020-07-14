package com.rihongo.map.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rihongo.map.model.dto.map.MapSearchRequestDto;
import com.rihongo.map.model.entities.MapSearchHistory;
import com.rihongo.map.model.repository.MapSearchHistoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindException;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MapControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Autowired
    private MapSearchHistoryRepository mapSearchHistoryRepository;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @WithMockUser("test1234")
    @Test
    public void searchTest() throws Exception {
        MapSearchRequestDto mapSearchRequestDto = new MapSearchRequestDto();
        mapSearchRequestDto.setQuery("test");
        mapSearchRequestDto.setPage(1);

        this.mvc.perform(get("/map/search")
                .queryParams(dtoConvertQueryParams(mapSearchRequestDto)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @WithMockUser("test1234")
    @Test
    public void searchQueryIsEmptyTestWithExpected() throws Exception {
        MapSearchRequestDto mapSearchRequestDto = new MapSearchRequestDto();
        mapSearchRequestDto.setQuery(null);

        this.mvc.perform(get("/map/search")
                .queryParams(dtoConvertQueryParams(mapSearchRequestDto)))
                .andDo(print())
                .andExpect(
                        (result) -> assertThat(
                                result.getResolvedException().getClass().getCanonicalName(),
                                equalTo(BindException.class.getCanonicalName())
                        )
                )
                .andReturn();
    }

    @WithMockUser("test1234")
    @Test
    public void getTopKeyword() throws Exception {
        this.mvc.perform(get("/map/top/keyword"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @WithMockUser("test1234")
    @Test
    public void saveTopKeyword() throws Exception {
        String keyword = "당산";
        this.mvc.perform(post("/map/top/keyword")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("keyword", keyword))
                .andDo(print())
                .andExpect(status().isCreated());

        MapSearchHistory mapSearchHistory = mapSearchHistoryRepository.findById(keyword).orElseThrow();

        assertThat(mapSearchHistory.getKeyword(), equalTo(keyword));

    }

    private MultiValueMap<String, String> dtoConvertQueryParams(MapSearchRequestDto mapSearchRequestDto) {
        ObjectMapper objectMapper = new ObjectMapper();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        Map<String, String> map = objectMapper.convertValue(mapSearchRequestDto, new TypeReference<>() {
        }); // (3)
        params.setAll(map);
        return params;
    }
}
