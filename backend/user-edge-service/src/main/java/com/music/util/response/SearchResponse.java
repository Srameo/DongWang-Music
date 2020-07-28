package com.music.util.response;

import com.music.domain.SongInfo;

import java.util.List;

public class SearchResponse extends Response {
    List<SongInfo> data;

    public SearchResponse(List<SongInfo> data) {
        this.data = data;
    }

    public List<SongInfo> getData() {
        return data;
    }

    public void setData(List<SongInfo> data) {
        this.data = data;
    }
}
