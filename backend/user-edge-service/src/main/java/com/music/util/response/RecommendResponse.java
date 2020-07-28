package com.music.util.response;

import java.util.List;

public class RecommendResponse extends Response {
    List<Long> ids;

    public RecommendResponse(List<Long> ids) {
        this.ids = ids;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
}
