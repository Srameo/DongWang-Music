package com.music.domain;

import java.util.List;

public class SongInfo {
    private int id;
    private String name;
    private int num;
    private List<SingerInfo> singers;
    private List<String> tags;

    @Override
    public String toString() {
        return "SongInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", num=" + num +
                ", singers=" + singers +
                ", tags=" + tags +
                '}';
    }

    public SongInfo() {
    }

    public SongInfo(int id, String name, int num) {
        this.id = id;
        this.name = name;
        this.num = num;
    }

    public SongInfo(int id, String name, int num, List<SingerInfo> singers, List<String> tags) {
        this.id = id;
        this.name = name;
        this.num = num;
        this.singers = singers;
        this.tags = tags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public List<SingerInfo> getSingers() {
        return singers;
    }

    public void setSingers(List<SingerInfo> singers) {
        this.singers = singers;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
