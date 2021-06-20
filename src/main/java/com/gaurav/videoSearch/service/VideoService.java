package com.gaurav.videoSearch.service;

import com.gaurav.videoSearch.entity.Video;
import com.gaurav.videoSearch.exception.DataNotPresentException;
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
public class VideoService {
    private final VideoRepository videoRepository;
    private static final EntityToBusinessObject mapper = new EntityToBusinessObject();
    public VideoResponses fetchAllVideo(Pageable pageable){
         Page<Video> videoList =  videoRepository.findAll(pageable);
         if (videoList.getSize() == 0){
             throw new DataNotPresentException("There does not exist any matching document : ");
         }
         List<VideoResponse> videoResponseList = videoList.stream().map(mapper).collect(Collectors.toList());
         return VideoResponses.builder()
                 .videoResponses(videoResponseList)
                 .build();
    }
}
