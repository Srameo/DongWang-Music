package com.music.service;

import com.music.domain.CommentInfo;
import com.music.domain.HistoryInfo;
import com.music.domain.StarInfo;
import com.music.util.date.GetDate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    @Test
    public void commentMusicTest(){
        Date currentDate = GetDate.getCurrentDate();
        CommentInfo commentInfo = new CommentInfo(1, 1, "aaaa", currentDate);
        musicService.commentMusic(commentInfo);
    }

    @Test
    public void cancelCommentMusic() throws ParseException {
        Date date = new SimpleDateFormat("yy-MM-dd HH:mm:ss").parse("2020-07-24 09:39:28");
        musicService.cancelCommentMusic(1, 1, date);
    }

    @Test
    public void recordTest(){
        HistoryInfo historyInfo = new HistoryInfo(1, 1, new Date());
        musicService.record(historyInfo);
    }

}
