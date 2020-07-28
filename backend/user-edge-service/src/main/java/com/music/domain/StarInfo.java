package com.music.domain;

import java.io.Serializable;
import java.util.Date;

public class StarInfo implements Serializable {

    private int uid;
    private int mid;
    private Date starTime;

    @Override
    public String toString() {
        return "StarInfo{" +
                "uid=" + uid +
                ", mid=" + mid +
                ", starTime=" + starTime +
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

    public Date getStarTime() {
        return starTime;
    }

    public void setStarTime(Date starTime) {
        this.starTime = starTime;
    }

    public StarInfo(int uid, int mid, Date starTime) {
        this.uid = uid;
        this.mid = mid;
        this.starTime = starTime;
    }

    public StarInfo() {
    }
}
