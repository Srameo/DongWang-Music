package com.music.util.response;

import com.music.domain.CommentInfo;
import com.music.domain.SongInfo;

public class MusicResponse extends Response {

    private SongInfo songInfo;

    public MusicResponse(SongInfo songInfo) {
        this.songInfo = songInfo;
    }

    public SongInfo getSongInfo() {
        return songInfo;
    }

    public void setSongInfo(SongInfo songInfo) {
        this.songInfo = songInfo;
    }
}
