package com.music.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class TestRecommendMapper {
    @Resource
    RecommendMapper recommendMapper;

    @Test
    void testRecommendMapper() {
        recommendMapper.set();
        System.out.println(recommendMapper.getHistoryList());
        System.out.println(recommendMapper.getMusicStyleList());
        System.out.println(recommendMapper.getNewMusicId());
        System.out.println(recommendMapper.getNewUserId());
    }
}
