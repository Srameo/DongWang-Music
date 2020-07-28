package com.music.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import com.music.domain.SparseMatrixElement;

import java.util.List;

@Component
public interface RecommendMapper {

    @Select("SELECT count(id) FROM users")
    Integer getUserNum();

    @Select("SELECT count(id) FROM music")
    Integer getMusicNum();

    @Select("SELECT count(id) FROM style")
    Integer getTagNum();

    @Select("SELECT id FROM users ORDER BY id")
    List<Integer> getNewUserId();

    @Select("SELECT id FROM music ORDER BY id")
    List<Integer> getNewMusicId();

    @Select("SELECT @curRank := 0, @pre := NULL;")
    List<Integer> set();

    @Select("select t.sid AS c ," +
            "       (@curRank := if(@pre=t.mid, @curRank , @curRank + 1)) AS r, " +
            "       @pre:= t.mid from music_style t " +
            "ORDER BY mid;")
    List<SparseMatrixElement> getMusicStyleList();

    @Select("SELECT uid AS r, mid AS c, COUNT(play_time) AS num FROM history_list " +
            "GROUP BY uid, mid ORDER BY mid;")
    List<SparseMatrixElement> getHistoryList();

}
