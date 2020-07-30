package com.music.service.impl;

import com.music.domain.*;
import com.music.mapper.MusicMapper;
import com.music.mapper.SearchMapper;
import com.music.service.MusicService;
import com.music.service.SearchService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class MusicServiceImpl implements MusicService {

    @Resource
    private MusicMapper musicMapper;

    @Resource
    private SearchMapper searchMapper;

    @Override
    public List<String> getAllTags() {
        return musicMapper.getAllTags();
    }

    @Override
    public void addNum(int id) {
        musicMapper.addNum(id);
    }

    @Override
    public List<SongInfo> getAllStars(int id) {
        List<SongInfo> songInfos = musicMapper.getAllStars(id);
        for (SongInfo songInfo : songInfos) {
            List<SingerInfo> singerInfos = searchMapper.getSingerInfoBySongId(songInfo.getId());
            songInfo.setSingers(singerInfos);
//            songInfo.setTags(searchMapper.getStylesByMusicId(songInfo.getId()));
        }
        return songInfos;
    }

    @Override
    public void starMusic(StarInfo starInfo) {
        musicMapper.starMusic(starInfo);
    }

    @Override
    public void cancelStarMusic(int uid, int mid) {
        musicMapper.cancelStarMusic(uid, mid);
    }

    @Override
    public void commentMusic(CommentInfo commentInfo) {
        musicMapper.commentMusic(commentInfo);
    }

    @Override
    public List<CommentInfo> getCommentsByMusicId(int id) {
        return musicMapper.getCommentsByMusicId(id);
    }

    @Override
    public void cancelCommentMusic(int uid, int mid, Date commentTime) {
        musicMapper.cancelCommentMusic(uid, mid, commentTime);
    }

    @Override
    public void record(HistoryInfo historyInfo) {
        musicMapper.record(historyInfo);
    }
}
