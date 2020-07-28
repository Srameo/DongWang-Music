package com.music.util.response;

import java.util.List;

public class RecommendResponse extends Response {
    List<Long> recommend_ids;

    public RecommendResponse(List<Long> recommend_ids) {
        this.recommend_ids = recommend_ids;
    }

    public List<Long> getRecommend_ids() {
        return recommend_ids;
    }

    public void setRecommend_ids(List<Long> recommend_ids) {
        this.recommend_ids = recommend_ids;
    }
}
