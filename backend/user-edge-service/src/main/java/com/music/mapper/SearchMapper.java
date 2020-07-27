package com.music.mapper;

import com.music.domain.SingerInfo;
import com.music.domain.SongInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SearchMapper {
    /**
     * 通过id查找歌曲信息
     */
    @Select("SELECT id, name, gender, location FROM singers WHERE id=#{id}")
    SingerInfo getSingerById(@Param("id") int id);

    /**
     * 通过歌曲id查找歌手id
     */
    @Select("SELECT sid FROM singers_music WHERE mid=#{id}")
    List<Integer> getMusicSingerById(@Param("id") int id);

    /**
     * 通过歌曲id获取歌曲信息
     */
    @Select("SELECT id, name, num FROM music WHERE id=#{id}")
    SongInfo getMusicInfoById(@Param("id") int id);

    /**
     * 通过歌曲id获取标签信息
     */
    @Select("SELECT detail FROM style WHERE id in (" +
            "   SELECT sid as id FROM music_style WHERE mid=#{id}" +
            ")")
    List<String> getStylesByMusicId(@Param("id") int id);

    /**
     * 通过标签名称获取标签id
     */
    @Select("SELECT id FROM style WHERE detail=#{name}")
    Integer getStyleIdByStyleName(@Param("name") String name);

    /**
     * 通过歌曲名称查找相关歌曲
     */
    @Select("SELECT id, name, num FROM music WHERE name like '%${name}%'")
    List<SongInfo> searchByKey(@Param("name") String name);


    /**
     * 通过歌手ID查找歌曲信息
     */
    @Select("SELECT id, name, num FROM music WHERE id in (" +
            "   SELECT mid as id FROM singer_music WHERE sid=#{id} " +
            ")")
    List<SongInfo> searchBySingerId(@Param("id") int id);

    /**
     * 通过标签id获取歌曲信息
     */
    @Select("SELECT id, name, num FROM music WHERE id in (" +
            "   SELECT mid as id FROM music_style WHERE sid=#{id}" +
            ")")
    List<SongInfo> searchByTagId(@Param("id") int id);

    /**
     * 查找是否有名为name的歌手，并返回id
     */
    @Select("SELECT id FROM singers WHERE name like '%${name}%'")
    Integer getSingerIdByName(@Param("name") String name);
}
