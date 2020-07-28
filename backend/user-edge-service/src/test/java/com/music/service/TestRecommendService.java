package com.music.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;

@SpringBootTest
public class TestRecommendService {
    @Resource
    RecommendService recommendService;

    @Test
    void TestSaveNewId() throws IOException {
        recommendService.saveNewUserId();
        recommendService.saveNewMusicId();
    }

    @Test
    void TestSaveSparseMatrix() throws IOException {
        recommendService.saveMusicSparseMatrix();
        recommendService.saveUserSparseMatrix();
    }

    @Test
    void TestRecommendDataFormat() throws InterruptedException, IOException, ClassNotFoundException {
        recommendService.recommendDataFormat();
    }

}
