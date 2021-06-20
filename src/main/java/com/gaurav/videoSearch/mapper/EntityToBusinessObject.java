package com.gaurav.videoSearch.mapper;

import com.gaurav.videoSearch.entity.Video;
import com.gaurav.videoSearch.reponse.VideoResponse;

import java.util.function.Function;

public class EntityToBusinessObject implements Function<Video, VideoResponse> {
    @Override
    public VideoResponse apply(Video video) {
        return VideoResponse.builder()
                .title(video.getTittle())
                .description(video.getDescription())
                .publishTime(video.getPublishTime())
                .build();
    }
}
