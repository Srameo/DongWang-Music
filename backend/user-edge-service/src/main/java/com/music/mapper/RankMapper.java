package com.music.mapper;

import com.music.domain.SongInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RankMapper {

    @Select("SELECT id, name, num FROM music WHERE id in (" +
            "   SELECT mid AS id FROM music_style WHERE sid in ( " +
            "       SELECT id AS sid FROM style WHERE detail LIKE \"%${detail}%\"" +
            "   )" +
            ") ORDER BY num DESC LIMIT #{limit}")
    List<SongInfo> getRankByTag(@Param("detail") String detail, @Param("limit") int limit);

    @Select("SELECT id, name, num FROM music ORDER BY num DESC LIMIT #{limit}")
    List<SongInfo> getAllRank(@Param("limit") int limit);
}
