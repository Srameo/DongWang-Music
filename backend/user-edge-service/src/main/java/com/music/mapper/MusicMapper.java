package com.music.mapper;

import com.music.domain.CommentInfo;
import com.music.domain.HistoryInfo;
import com.music.domain.StarInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

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
    @Insert("insert into comment(uid,mid,content,comment_time) " +
            "values(#{c.uid},#{c.mid},#{c.content},#{c.commentTime})")
    void commentMusic(@Param("c") CommentInfo commentInfo);

    @Select("SELECT detail FROM style")
    List<String> getAllTags();

    /**
     * 删除评论
     */
    @Delete("delete from comment where uid = #{u} and mid = #{m} and comment_time = #{c}")
    void cancelCommentMusic(@Param("u") int uid, @Param("m") int mid, @Param("c") Date commentTime);

    /**
     * 记录用户播放记录
     */
    @Insert("insert into history_list(uid,mid,play_time) values(#{h.uid},#{h.mid},#{h.playTime})")
    void record(@Param("h") HistoryInfo historyInfo);

}
