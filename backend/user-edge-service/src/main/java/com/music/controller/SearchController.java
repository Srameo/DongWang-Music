package com.music.controller;

import com.music.domain.SongInfo;
import com.music.service.SearchService;
import com.music.util.response.Response;
import com.music.util.response.SearchResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class SearchController {

    @Resource
    SearchService searchService;

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public Response search(@RequestParam("key") String key) {
        List<SongInfo> res = searchService.search(key);
        return new SearchResponse(res);
    }

}
