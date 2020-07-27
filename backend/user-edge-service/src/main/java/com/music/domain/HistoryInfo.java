package com.music.domain;

import java.io.Serializable;
import java.util.Date;

public class HistoryInfo implements Serializable {

    private int uid;
    private int mid;
    private Date playTime;


    @Override
    public String toString() {
        return "HistoryInfo{" +
                "uid=" + uid +
                ", mid=" + mid +
                ", playTime=" + playTime +
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

    public Date getPlayTime() {
        return playTime;
    }

    public void setPlayTime(Date playTime) {
        this.playTime = playTime;
    }

    public HistoryInfo(int uid, int mid, Date playTime) {
        this.uid = uid;
        this.mid = mid;
        this.playTime = playTime;
    }

    public HistoryInfo() {
    }
}
