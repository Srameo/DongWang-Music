package com.music.controller;

import com.music.mapper.RecommendMapper;
import com.music.service.RecommendService;
import com.music.thrift.ServiceProvider;
import com.music.util.response.RecommendResponse;
import com.music.util.response.Response;
import org.apache.thrift.TException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/recommend")
@CrossOrigin
public class RecommendController {

    @Resource
    RecommendMapper recommendMapper;

    @Resource
    RecommendService recommendService;

    @Resource
    private ServiceProvider serviceProvider;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseBody
    public Response getUserRecommendDetail(@RequestParam("id") long uid) {
        com.music.thrift.recommend.RecommendService.Client rs = serviceProvider.getRecommendService();
        List<Long> recommendList = null;
        try {
            recommendList = rs.getUserRecommendDetail(uid);
        } catch (TException e) {
            e.printStackTrace();
            return Response.RECOMMEND_ERROR;
        }
        return new RecommendResponse(recommendList);
    }

    @RequestMapping(value = "/music", method = RequestMethod.POST)
    @ResponseBody
    public Response getMusicRecommendDetail(@RequestParam("id") long mid) {
        com.music.thrift.recommend.RecommendService.Client rs = serviceProvider.getRecommendService();
        List<Long> recommendList = null;
        try {
            recommendList = rs.getMusicRecommendDetail(mid);
        } catch (TException e) {
            e.printStackTrace();
            return Response.RECOMMEND_ERROR;
        }
        return new RecommendResponse(recommendList);
    }

    @RequestMapping(value = "/refreshUserList", method = RequestMethod.POST)
    @ResponseBody
    public Response recommendUser() {
        try {
            recommendService.recommendDataFormat();
        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
            return Response.RECOMMEND_ERROR;
        }
        com.music.thrift.recommend.RecommendService.Client rs = serviceProvider.getRecommendService();
        try {
            boolean ok = rs.recommendUser(recommendMapper.getUserNum(), recommendMapper.getMusicNum());
            return ok ? Response.SUCCESS : Response.RECOMMEND_ERROR;
        } catch (TException e) {
            e.printStackTrace();
            return Response.RECOMMEND_ERROR;
        }
    }

    @RequestMapping(value = "/refreshMusicList", method = RequestMethod.POST)
    @ResponseBody
    public Response recommendMusic() {
        try {
            recommendService.recommendDataFormat();
        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
            return Response.RECOMMEND_ERROR;
        }
        com.music.thrift.recommend.RecommendService.Client rs = serviceProvider.getRecommendService();
        try {
            boolean ok = rs.recommendMusic(recommendMapper.getMusicNum(), recommendMapper.getTagNum());
            return ok ? Response.SUCCESS : Response.RECOMMEND_ERROR;
        } catch (TException e) {
            e.printStackTrace();
            return Response.RECOMMEND_ERROR;
        }
    }

}
