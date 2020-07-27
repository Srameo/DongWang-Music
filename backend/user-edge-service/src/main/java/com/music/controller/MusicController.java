package com.music.controller;

import com.music.domain.CommentInfo;
import com.music.domain.StarInfo;
import com.music.service.MusicService;
import com.music.util.date.GetDate;
import com.music.util.response.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

@Controller
@RequestMapping("/music")
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
