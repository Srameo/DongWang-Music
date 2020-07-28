package com.music.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class TestSearchMapper {

    @Resource
    SearchMapper searchMapper;

    @Test
    void testSearchSingerInfoBySongId() {
        System.out.println(searchMapper.getSingerInfoBySongId(60008));
    }
}
