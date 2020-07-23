package com.music.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class TestSearchService {
    @Resource
    private SearchService searchService;

    @Test
    public void searchByKey() {
        System.out.println(searchService.search("song"));
        System.out.println(searchService.search("lyh"));
        System.out.println(searchService.search("流行"));
    }
}
