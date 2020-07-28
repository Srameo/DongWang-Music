package com.music.controller;

import com.music.domain.CommentInfo;
import com.music.domain.StarInfo;
import com.music.service.MusicService;
import com.music.util.date.GetDate;
import com.music.util.response.Response;
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

}
