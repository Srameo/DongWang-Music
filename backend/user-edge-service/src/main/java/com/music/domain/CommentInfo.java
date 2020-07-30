package com.music.domain;

import java.io.Serializable;
import java.util.Date;

public class CommentInfo implements Serializable {

    private int uid;
    private int mid;
    String content;
    Date commentTime;

    @Override
    public String toString() {
        return "CommentInfo{" +
                "uid=" + uid +
                ", mid=" + mid +
                ", content='" + content + '\'' +
                ", commentTime=" + commentTime +
                '}';
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public CommentInfo(int uid, int mid, String content, Date commentTime) {
        this.uid = uid;
        this.mid = mid;
        this.content = content;
        this.commentTime = commentTime;
    }

    public CommentInfo() {
    }
}
