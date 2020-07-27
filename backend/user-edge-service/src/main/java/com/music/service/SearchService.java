package com.music.service;

import com.music.domain.SongInfo;

import java.util.List;

public interface SearchService {
    List<SongInfo> searchByKey(String key);
    List<SongInfo> searchByTag(int id);
    List<SongInfo> searchBySinger(int id);
    public List<SongInfo> search(String str);
}
