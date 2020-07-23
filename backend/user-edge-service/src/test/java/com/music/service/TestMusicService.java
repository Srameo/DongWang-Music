package com.music.service;

import com.music.domain.StarInfo;
import com.music.util.date.GetDate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;

@SpringBootTest
public class TestMusicService {

    @Resource
    private MusicService musicService;

    @Test
    public void starMusicTest(){
        Date currentDate = GetDate.getCurrentDate();
        StarInfo starInfo = new StarInfo(1, 1, currentDate);
        musicService.starMusic(starInfo);
    }

    @Test
    public void cancelStarMusic(){
        musicService.cancelStarMusic(1, 1);
    }

}
