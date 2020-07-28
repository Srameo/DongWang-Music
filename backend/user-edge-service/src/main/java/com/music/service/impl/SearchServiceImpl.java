package com.music.service.impl;

import com.music.domain.SingerInfo;
import com.music.domain.SongInfo;
import com.music.mapper.SearchMapper;
import com.music.service.SearchService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Resource
    private SearchMapper searchMapper;

    @Override
    public List<SongInfo> searchByKey(String key) {
        return searchMapper.searchByKey(key);
    }

    @Override
    public List<SongInfo> searchByTag(int id) {
        return searchMapper.searchByTagId(id);
    }

    @Override
    public List<SongInfo> searchBySinger(int id) {
        return searchMapper.searchBySingerId(id);
    }

    @Override
    public List<SongInfo> search(String str) {
        Integer styleId = searchMapper.getStyleIdByStyleName(str);
        List<SongInfo> songInfos = null;
        if (styleId == null) {
            Integer singerId = searchMapper.getSingerIdByName(str);
            if (singerId == null) {
                songInfos = searchByKey(str);
            } else {
                songInfos = searchBySinger(singerId);
            }
        } else {
            songInfos = searchByTag(styleId);
        }
        for (SongInfo songInfo : songInfos) {
            List<SingerInfo> singerInfos = searchMapper.getSingerInfoBySongId(songInfo.getId());
            songInfo.setSingers(singerInfos);
        }
        return songInfos;
    }
}
