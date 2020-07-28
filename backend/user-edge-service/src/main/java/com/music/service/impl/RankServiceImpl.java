package com.music.service.impl;

import com.music.domain.SongInfo;
import com.music.mapper.RankMapper;
import com.music.mapper.SearchMapper;
import com.music.service.RankService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RankServiceImpl implements RankService {

    @Resource
    SearchMapper searchMapper;

    @Resource
    RankMapper rankMapper;

    @Override
    public List<SongInfo> getAllRank(int limit) {
        List<SongInfo> songInfos = rankMapper.getAllRank(limit);
        for (SongInfo songInfo : songInfos) {
            songInfo.setSingers(searchMapper.getSingerInfoBySongId(songInfo.getId()));
        }
        return songInfos;
    }

    @Override
    public List<SongInfo> getTagRank(String tag, int limit) {
        List<SongInfo> songInfos = rankMapper.getRankByTag(tag, limit);
        for (SongInfo songInfo : songInfos) {
            songInfo.setSingers(searchMapper.getSingerInfoBySongId(songInfo.getId()));
        }
        return songInfos;
    }
}
