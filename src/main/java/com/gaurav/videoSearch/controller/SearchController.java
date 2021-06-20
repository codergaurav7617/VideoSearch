package com.gaurav.videoSearch.controller;

import com.gaurav.videoSearch.reponse.VideoResponses;
import com.gaurav.videoSearch.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class SearchController {
    @Autowired private SearchService searchService;
    @GetMapping("/search")
    public ResponseEntity<VideoResponses> searchByKeyword(@RequestParam Map<String,String> requestMap){
        return ResponseEntity.ok(searchService.searchByKeyword(requestMap.get("keyword"), ControllerUtil.getPageable(requestMap)));
    }
}
