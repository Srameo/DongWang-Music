package com.music.domain;

import java.io.Serializable;

public class UserStyleInfo implements Serializable {

    private int uid;
    private int sid;

    @Override
    public String toString() {
        return "UserStyleInfo{" +
                "uid=" + uid +
                ", sid=" + sid +
                '}';
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public UserStyleInfo(int uid, int sid) {
        this.uid = uid;
        this.sid = sid;
    }

    public UserStyleInfo() {
    }
}
