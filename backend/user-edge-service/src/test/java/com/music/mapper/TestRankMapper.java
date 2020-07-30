package com.music.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class TestRankMapper {

    @Resource
    RankMapper rankMapper;

    @Test
    void testGetRankByTag() {
        System.out.println(rankMapper.getRankByTag("欧美", 10));
        System.out.println(rankMapper.getAllRank(10));
    }

}
