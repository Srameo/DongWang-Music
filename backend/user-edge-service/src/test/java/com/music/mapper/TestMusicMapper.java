package com.music.mapper;

import com.music.domain.StarInfo;
import com.music.util.date.GetDate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;

@SpringBootTest
public class TestMusicMapper {

    @Resource
    private MusicMapper musicMapper;

    @Test
    public void starMusicTest(){
        Date currentDate = GetDate.getCurrentDate();
        StarInfo starInfo = new StarInfo(1, 1, currentDate);
        musicMapper.starMusic(starInfo);
    }

    @Test
    public void cancelStarMusicTest(){
        musicMapper.cancelStarMusic(1, 1);
    }

}
