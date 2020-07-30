package com.music.domain;

public class SingerInfo {
    private int id;
    private String name;
    private int gender;
    private String location;

    public SingerInfo() {
    }

    @Override
    public String toString() {
        return "SingerInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", location='" + location + '\'' +
                '}';
    }

    public SingerInfo(int id, String name, int gender, String location) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.location = location;
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

    public int isGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
