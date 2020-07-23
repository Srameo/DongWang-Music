package com.music.mapper;

import com.music.domain.StarInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface MusicMapper {

    /**
     * 收藏歌曲
     */
    @Insert("insert into stars(uid, mid, star_time) " +
            "values(#{s.uid}, #{s.mid}, #{s.starTime})")
    void starMusic(@Param("s") StarInfo starInfo);

    /**
     * 取消收藏歌曲
     */
    @Delete("delete from stars where uid = #{u} and mid = #{m}")
    void cancelStarMusic(@Param("u") int uid, @Param("m") int mid);

    /**
     * 评论歌曲
     */
    void commentMusic(@Param("id") int id, @Param("content") String content);

}
