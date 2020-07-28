package com.music.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class TestRankService {

    @Resource
    RankService rankService;

    @Test
    void TestRankService() {
        System.out.println(rankService.getAllRank(10));
        System.out.println(rankService.getTagRank("欧美", 10));
    }
}
