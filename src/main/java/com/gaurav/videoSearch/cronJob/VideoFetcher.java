package com.gaurav.videoSearch.cronJob;

import com.gaurav.videoSearch.entity.Item;
import com.gaurav.videoSearch.entity.Response;
import com.gaurav.videoSearch.entity.Video;
import com.gaurav.videoSearch.mapper.SnippetToVideoEntityConverter;
import com.gaurav.videoSearch.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import static com.gaurav.videoSearch.constant.Constant.API_KEY;
import static com.gaurav.videoSearch.constant.Constant.BASE_URL;
import static com.gaurav.videoSearch.constant.Constant.MAX_RESULT;
import static com.gaurav.videoSearch.constant.Constant.PART;
import static com.gaurav.videoSearch.constant.Constant.QUERY;

@Component
public class VideoFetcher {
    @Autowired  private  VideoRepository videoRepository;
    @Autowired private  RestTemplate restTemplate;
    private static final SnippetToVideoEntityConverter mapper = new SnippetToVideoEntityConverter();

    @Scheduled(fixedRateString = "${video.fetch.reloadTimeInMs}")
    public void videoFetcherScheduler(){
        ResponseEntity<Response> responseEntity = restTemplate.getForEntity(getResourceUrl(), Response.class);
        List<Item> itemList = Objects.requireNonNull(responseEntity.getBody()).getItems();
        List<Video> videoList = itemList.stream().parallel().map(mapper).collect(Collectors.toList());
        videoRepository.saveAll(videoList);
    }

    private String getResourceUrl(){
        ZonedDateTime zonedDateTime = ZonedDateTime.now().minusMinutes(1);
        String dateTime =DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss").format(zonedDateTime)+".454-07:00";
        return BASE_URL+"?part="+PART+"&maxResults="+MAX_RESULT+"&publishedAfter="+dateTime+"&q="+QUERY+"&key="+API_KEY;
    }
}
