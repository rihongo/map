package com.rihongo.map.controller;

import com.rihongo.map.model.dto.CommonResponseDto;
import com.rihongo.map.model.dto.map.MapSearchRequestDto;
import com.rihongo.map.service.MapService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/map")
@Slf4j
@RequiredArgsConstructor
public class MapController {

    private final MapService mapService;

    @GetMapping("")
    public String index() {
        return "/contents/index";
    }

    @GetMapping("search")
    @ResponseBody
    public ResponseEntity search(@ModelAttribute @Valid MapSearchRequestDto mapSearchRequestDto) {
        return new ResponseEntity(
                CommonResponseDto
                        .builder()
                        .data(mapService.search(mapSearchRequestDto))
                        .build()
                , HttpStatus.OK);
    }

    @GetMapping("top/keyword")
    @ResponseBody
    public ResponseEntity topKeyword() {
        return new ResponseEntity(
                CommonResponseDto
                        .builder()
                        .data(mapService.findTopKeyword())
                        .build()
                , HttpStatus.OK);
    }

    @PostMapping("top/keyword")
    @ResponseBody
    public ResponseEntity saveKeyword(String keyword) {
        mapService.saveKeyword(keyword);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
