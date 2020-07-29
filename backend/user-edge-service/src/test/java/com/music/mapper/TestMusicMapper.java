package com.music.mapper;

import com.music.domain.CommentInfo;
import com.music.domain.StarInfo;
import com.music.util.date.GetDate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    @Test
    public void commentMusicTest(){
        Date currentDate = GetDate.getCurrentDate();
        CommentInfo commentInfo = new CommentInfo(1, 1, "aaaa", currentDate);
        musicMapper.commentMusic(commentInfo);
    }

    @Test
    public void cancelCommentMusic() throws ParseException {
        Date date = new SimpleDateFormat("yy-MM-dd HH:mm:ss").parse("2020-07-24 09:35:04");
        musicMapper.cancelCommentMusic(1, 1, date);
    }

    @Test
    public void testGetAllStars() {
        System.out.println(musicMapper.getAllStars(5));
    }
}
