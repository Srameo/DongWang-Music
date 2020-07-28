package com.music.controller;

import com.music.service.RecommendService;
import com.music.util.response.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/recommend")
public class RecommendController {

    @Resource
    RecommendService recommendService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Response getUserRecommendDetail(@RequestParam("id") long uid) {
        return null;
    }

}
