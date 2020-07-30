package com.music.controller;

import com.music.domain.SongInfo;
import com.music.service.RankService;
import com.music.util.response.Response;
import com.music.util.response.SearchResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Component
@RequestMapping("/rank")
@CrossOrigin
public class RankController {
    @Resource
    RankService rankService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public Response getAllRank(@RequestParam("limit") int limit) {
        List<SongInfo> songInfoList = rankService.getAllRank(limit);
        return new SearchResponse(songInfoList);
    }

    @RequestMapping(value = "/tag", method = RequestMethod.GET)
    @ResponseBody
    public Response getTagRank(@RequestParam("tag") String tag, @RequestParam("limit") int limit) {
        List<SongInfo> songInfoList = rankService.getTagRank(tag, limit);
        return new SearchResponse(songInfoList);
    }
}
