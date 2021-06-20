package com.gaurav.videoSearch.service;

import com.gaurav.videoSearch.entity.Video;
import com.gaurav.videoSearch.mapper.EntityToBusinessObject;
import com.gaurav.videoSearch.reponse.VideoResponse;
import com.gaurav.videoSearch.reponse.VideoResponses;
import com.gaurav.videoSearch.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchService {
    private static final EntityToBusinessObject mapper = new EntityToBusinessObject();
    private final VideoRepository videoRepository;
    public VideoResponses searchByKeyword(String keyword, Pageable pageable){
        Page<Video> videoList =  videoRepository.findByTittleOrDescription(keyword, keyword,pageable );
        List<VideoResponse> videoResponseList = videoList.stream().map(mapper).collect(Collectors.toList());
        return VideoResponses.builder()
                .videoResponses(videoResponseList)
                .build();
    }
}
