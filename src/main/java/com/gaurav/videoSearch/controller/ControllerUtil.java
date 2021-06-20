package com.gaurav.videoSearch.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Map;

import static com.gaurav.videoSearch.constant.Constant.PAGE_NUMBER;
import static com.gaurav.videoSearch.constant.Constant.PAGE_SIZE;

public class ControllerUtil {
    public static Pageable getPageable(Map<String, String> requestParamMap)
    {
        Sort sort = Sort.by("timeInEpoch").descending();
        Pageable pageRequest;
        if (requestParamMap.containsKey(PAGE_SIZE) && requestParamMap.containsKey(PAGE_NUMBER))
        {
            int pageSize = Integer.parseInt(requestParamMap.get(PAGE_SIZE));
            int pageIndex = Integer.parseInt(requestParamMap.get(PAGE_NUMBER));
            pageRequest = PageRequest.of(Math.max(pageIndex-1,0) , pageSize, sort);
        }
        else
        {
            pageRequest = PageRequest.of(0, 5000, sort);
        }
        return pageRequest;
    }
}
