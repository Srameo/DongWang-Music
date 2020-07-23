package com.music.service.impl;

import com.music.domain.StarInfo;
import com.music.mapper.MusicMapper;
import com.music.service.MusicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MusicServiceImpl implements MusicService {

    @Resource
    private MusicMapper musicMapper;

    @Override
    public void starMusic(StarInfo starInfo) {
        musicMapper.starMusic(starInfo);
    }

    @Override
    public void cancelStarMusic(int uid, int mid) {
        musicMapper.cancelStarMusic(uid, mid);
    }
}
