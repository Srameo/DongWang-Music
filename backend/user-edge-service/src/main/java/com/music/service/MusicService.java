package com.music.service;

import com.music.domain.StarInfo;
import org.apache.ibatis.annotations.Param;

public interface MusicService {

    /**
     * 收藏歌曲
     */
    void starMusic(StarInfo starInfo);

    /**
     * 取消收藏歌曲
     */
    void cancelStarMusic(int uid, int mid);

}
