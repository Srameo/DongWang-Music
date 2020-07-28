package com.music.domain;

public class SparseMatrixElement {

    Integer r;
    Integer c;
    Integer num = 1;

    public SparseMatrixElement(Integer r, Integer c) {
        this.r = r;
        this.c = c;
    }

    @Override
    public String toString() {
        return String.valueOf(r) + "\t" + String.valueOf(c) + "\t" + String.valueOf(num);
    }

    public int getR() {
        return r;
    }

    public void setR(Integer r) {
        this.r = r;
    }

    public int getC() {
        return c;
    }

    public void setC(Integer c) {
        this.c = c;
    }

    public int getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public SparseMatrixElement(Integer r, Integer c, Integer num) {
        this.r = r;
        this.c = c;
        this.num = num;
    }

    public SparseMatrixElement() {
    }
}
