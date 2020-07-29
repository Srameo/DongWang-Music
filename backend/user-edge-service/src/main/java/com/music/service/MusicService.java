package com.music.service;

import com.music.domain.CommentInfo;
import com.music.domain.HistoryInfo;
import com.music.domain.StarInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface MusicService {

    List<String> getAllTags();

    void addNum(int id);

    /**
     * 收藏歌曲
     */
    void starMusic(StarInfo starInfo);

    /**
     * 取消收藏歌曲
     */
    void cancelStarMusic(int uid, int mid);

    /**
     * 评论歌曲
     */
    void commentMusic(CommentInfo commentInfo);

    List<CommentInfo> getCommentsByMusicId(int id);

    /**
     * 删除评论
     */
    void cancelCommentMusic(int uid, int mid, Date commentTime);

    /**
     * 记录用户播放记录
     */
    void record(HistoryInfo historyInfo);

}
