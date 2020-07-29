package com.music.controller;

import com.music.domain.CommentInfo;
import com.music.domain.HistoryInfo;
import com.music.domain.SongInfo;
import com.music.domain.StarInfo;
import com.music.mapper.SearchMapper;
import com.music.service.MusicService;
import com.music.util.date.GetDate;
import com.music.util.response.CommentsResponse;
import com.music.util.response.MusicResponse;
import com.music.util.response.Response;
import com.music.util.response.SearchResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/music")
@CrossOrigin
public class MusicController {

    @Resource
    private MusicService musicService;

    @Resource
    private SearchMapper searchMapper;

    @RequestMapping(value = "/getstars", method = RequestMethod.POST)
    @ResponseBody
    public Response getAllStars(@RequestParam("id") int id) {
        List<SongInfo> songInfos = musicService.getAllStars(id);
        return new SearchResponse(songInfos);
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public Response getMusic(@RequestParam("id") int id) {
        SongInfo songInfo = searchMapper.getMusicInfoById(id);
        if (songInfo != null) {
            songInfo.setSingers(searchMapper.getSingerInfoBySongId(id));
            songInfo.setTags(searchMapper.getStylesByMusicId(id));
        }
        return new MusicResponse(songInfo);
    }

    @RequestMapping(value = "/get/comments", method = RequestMethod.POST)
    @ResponseBody
    public Response getMusicComments(@RequestParam("id") int id) {
        List<CommentInfo> commentInfos = musicService.getCommentsByMusicId(id);
        return new CommentsResponse(commentInfos);
    }

    @RequestMapping(value = "/star", method = RequestMethod.POST)
    @ResponseBody
    public Response starMusic(@RequestParam("uid") int uid, @RequestParam("mid") int mid){
        Date currentDate = GetDate.getCurrentDate();
        StarInfo starInfo = new StarInfo(uid, mid, currentDate);
        musicService.starMusic(starInfo);
        return Response.SUCCESS;
    }

    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getAllTags() {
        return musicService.getAllTags();
    }

    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    @ResponseBody
    public Response saveComment(@RequestParam("uid") int uid,
                                @RequestParam("mid") int mid,
                                @RequestParam("text") String text){
        Date currentDate = GetDate.getCurrentDate();
        CommentInfo commentInfo = new CommentInfo(uid, mid, text, currentDate);
        musicService.commentMusic(commentInfo);
        return Response.SUCCESS;
    }

    @RequestMapping(value = "/history", method = RequestMethod.POST)
    @ResponseBody
    public Response saveHistory(@RequestParam("uid") int uid,
                                @RequestParam("mid") int mid) {
        musicService.record(new HistoryInfo(uid, mid, new Date()));
        return Response.SUCCESS;
    }

    @RequestMapping(value = "/play", method = RequestMethod.POST)
    @ResponseBody
    public Response play(@RequestParam("id") int id) {
        musicService.addNum(id);
        return Response.SUCCESS;
    }

}
