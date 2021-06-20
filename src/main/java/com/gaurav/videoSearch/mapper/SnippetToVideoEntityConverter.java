package com.gaurav.videoSearch.mapper;

import com.gaurav.videoSearch.entity.Item;
import com.gaurav.videoSearch.entity.Snippet;
import com.gaurav.videoSearch.entity.Video;
import java.util.function.Function;

public class SnippetToVideoEntityConverter implements Function<Item, Video> {
    @Override
    public Video apply(Item item) {
        Snippet snippet = item.getSnippet();
        return Video.builder()
                .tittle(snippet.getTitle())
                .description(snippet.getDescription())
                .publishTime(snippet.getPublishTime())
                .build();
    }
}