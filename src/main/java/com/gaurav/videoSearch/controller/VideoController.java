package com.gaurav.videoSearch.controller;

import com.gaurav.videoSearch.reponse.VideoResponses;
import com.gaurav.videoSearch.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController("/v1")
public class VideoController {
    @Autowired private VideoService videoService;
    @GetMapping("/fetchAll")
    public ResponseEntity<VideoResponses> fetchAllVideo(@RequestParam Map<String,String> requestMap){
        return ResponseEntity.
                ok(
                        videoService.fetchAllVideo(ControllerUtil.getPageable(requestMap))
                );
    }

}
