package com.gaurav.videoSearch.repository;

import com.gaurav.videoSearch.entity.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends ElasticsearchRepository<Video, Long> {
    Page<Video> findByTittleOrDescription(String tittle, String description, Pageable pageable);
}
