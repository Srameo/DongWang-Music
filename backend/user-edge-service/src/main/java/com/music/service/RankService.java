package com.music.service;

import com.music.domain.SongInfo;

import java.util.List;

public interface RankService {
    List<SongInfo> getAllRank(int limit);
    List<SongInfo> getTagRank(String tag, int limit);
}
